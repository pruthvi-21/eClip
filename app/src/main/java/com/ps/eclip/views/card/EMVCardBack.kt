package com.ps.eclip.views.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.ps.eclip.databinding.EmvCardBackBinding
import com.ps.eclip.utils.Constants.EMV_CARD_RATIO
import com.ps.eclip.utils.Utils

class EMVCardBack @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAtr: Int = 0
) : FrameLayout(context, attrs, defStyleAtr) {

    private val cardWidth: Float
    private val cardHeight: Float

    private val binding: EmvCardBackBinding

    init {
        val width = Utils.getScreenSize(context)[0]
        cardWidth = width * .8f
        cardHeight = cardWidth * 1 / EMV_CARD_RATIO

        binding = EmvCardBackBinding.inflate(LayoutInflater.from(context), this, true)

        binding.mainContainer.layoutParams = LayoutParams(cardWidth.toInt(), cardHeight.toInt())
        binding.mainContainer.radius = cardWidth * 24f / 343f
    }

    companion object {
        private const val TAG = "EMVCardBack"
    }
}