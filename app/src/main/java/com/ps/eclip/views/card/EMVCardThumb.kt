package com.ps.eclip.views.card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.ps.eclip.databinding.EmvCardThumbBinding

class EMVCardThumb @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAtr: Int = 0
) : FrameLayout(context, attrs, defStyleAtr) {

    private val binding = EmvCardThumbBinding.inflate(LayoutInflater.from(context), this, true)

    companion object {
        private const val TAG = "EMVCard"
    }
}