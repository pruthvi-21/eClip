package com.ps.eclip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ps.eclip.models.EMVCardPreviewModel
import com.ps.eclip.utils.CardSchemeIdentifier
import com.ps.eclip.utils.Utils

class CardsAdapter(
    private val list: List<EMVCardPreviewModel>
) : RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    fun interface OnItemClickListener {
        fun onClick(position: Int)
    }

    private var listener: OnItemClickListener? = null

    inner class CardsViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val cardLabel = itemView.findViewById<TextView>(R.id.card_label)
        private val cardNumberPreview = itemView.findViewById<TextView>(R.id.card_num_preview)
        private val cardIcon = itemView.findViewById<ImageView>(R.id.card_icon)

        init {
            itemView.setOnClickListener {
                listener?.onClick(adapterPosition)
            }
        }

        fun bind(card: EMVCardPreviewModel) {
            cardLabel.text = card.cardLabel
            cardNumberPreview.text = Utils.formatCardNumberPreview(card.cardNumber)

            val num = card.cardNumber.toString()
            val scheme = CardSchemeIdentifier.match(num)
            cardIcon.setImageResource(scheme.iconRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.emv_card_thumb, parent, false)
        return CardsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
