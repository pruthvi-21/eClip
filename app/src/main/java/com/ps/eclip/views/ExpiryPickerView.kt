package com.ps.eclip.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.ps.eclip.databinding.CardExpiryViewBinding
import com.ps.eclip.utils.ExpiryPickerDialog
import com.ps.eclip.utils.Utils

class ExpiryPickerView(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs), View.OnClickListener {

    private val binding = CardExpiryViewBinding.inflate(LayoutInflater.from(context), this, true)

    var month: Int? = null
    var year: Int? = null

    init {
        binding.dateView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        ExpiryPickerDialog(context, month, year).apply {
            setListener { m, y ->
                month = m
                year = y
                binding.dateView.text = Utils.formatExpiryDate(m, y)
            }
            create()
            show()
        }
    }
}