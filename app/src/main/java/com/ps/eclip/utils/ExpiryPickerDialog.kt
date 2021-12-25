package com.ps.eclip.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.ps.eclip.R
import com.ps.eclip.databinding.DialogMonthYearPickerBinding
import com.ps.eclip.interfaces.OnExpiryDateSetListener
import java.util.*

class ExpiryPickerDialog @JvmOverloads constructor(
    context: Context,
    private val month: Int? = null,
    private val year: Int? = null
) : AlertDialog(context, R.style.DialogTheme) {

    private val binding: DialogMonthYearPickerBinding
    private var listener: OnExpiryDateSetListener? = null

    init {
        window?.apply {
            attributes?.apply {
                gravity = Gravity.BOTTOM
                y = 40
            }
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        binding = DialogMonthYearPickerBinding.inflate(LayoutInflater.from(context))
        setView(binding.root)

        val calendar = Calendar.getInstance()
        binding.monthPicker.run {
            wrapSelectorWheel = false
            minValue = 1
            maxValue = 12
            value = calendar.get(Calendar.MONTH)
            displayedValues = resources.getStringArray(R.array.ExpiryMonths)

            if (month != null && month in 1..12) {
                value = month
            }
        }

        binding.yearPicker.run {
            wrapSelectorWheel = false
            val y = calendar.get(Calendar.YEAR)
            minValue = y
            maxValue = y + 20
            value = y

            if (year != null && year in minValue..maxValue) {
                value = year
            }
        }

        binding.cancelBtn.setOnClickListener {
            cancel()
        }

        binding.okBtn.setOnClickListener {
            listener?.onExpiryDateSet(
                binding.monthPicker.value,
                binding.yearPicker.value
            )
            dismiss()
        }
    }

    fun setListener(listener: OnExpiryDateSetListener?): ExpiryPickerDialog {
        this.listener = listener
        return this
    }
}