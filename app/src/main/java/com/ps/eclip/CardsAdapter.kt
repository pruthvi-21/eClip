package com.ps.eclip

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ps.eclip.models.EMVCardPreviewModel
import com.ps.eclip.utils.Utils

class CardsAdapter(
    private val list: List<EMVCardPreviewModel>
) : RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    inner class CardsViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val cardLabel = itemView.findViewById<TextView>(R.id.card_label)
        private val cardNumberPreview = itemView.findViewById<TextView>(R.id.card_num_preview)
        private val cardIcon = itemView.findViewById<ImageView>(R.id.card_icon)

        @SuppressLint("SetTextI18n")
        fun bind(card: EMVCardPreviewModel) {
            cardLabel.text = card.cardLabel
            cardNumberPreview.text = Utils.formatCardNumber(card.cardNumber)

            val num = card.cardNumber.toString()
            cardIcon.setImageResource(
                when {
                    num.startsWith("4") -> R.drawable.ic_visa
                    num.startsWith("5") -> R.drawable.ic_mastercard
                    num.startsWith("6") -> R.drawable.ic_rupay
                    num.startsWith("1") -> R.drawable.ic_amex
                    num.startsWith("2") -> R.drawable.ic_discover
                    else -> R.drawable.ic_emv_icon
                }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.emv_card_thumb_new, parent, false)
        return CardsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
