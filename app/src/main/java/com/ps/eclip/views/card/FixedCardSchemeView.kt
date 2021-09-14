package com.ps.eclip.views.card

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.ps.eclip.utils.Utils

class FixedCardSchemeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    init {
        scaleType = ScaleType.FIT_CENTER
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val screenSize = Utils.getScreenSize(context)
        val reqWidth = screenSize[0] * .8f * .15f
        setMeasuredDimension(reqWidth.toInt(), reqWidth.toInt())
    }
}