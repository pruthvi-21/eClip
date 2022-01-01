package com.ps.eclip.views.card

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.ps.eclip.R
import com.ps.eclip.utils.Utils

class FixedSizeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    init {
        val screenSize = Utils.getScreenSize(context)
        val cardWidth = screenSize[0] * .8f

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FixedSizeTextView)

            val factor = typedArray.getInt(R.styleable.FixedSizeTextView_text_size, -1)
            if (factor != -1) {
                val sizeFactor = when (factor) {
                    0 -> 0.033f //small
                    1 -> 0.04f //medium
                    else -> 0.048f //large
                }
                val textSize = cardWidth * sizeFactor
                setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
            }
            typedArray.recycle()
        }

        maxLines = 1
        letterSpacing = 0.2f
        typeface = ResourcesCompat.getFont(context, R.font.cc_regular)
    }
}