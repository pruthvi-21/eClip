package com.ps.eclip.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ps.eclip.R
import com.ps.eclip.databinding.CardExpiryViewBinding
import com.ps.eclip.databinding.DialogMonthYearPickerBinding
import com.ps.eclip.utils.Utils
import java.util.Calendar

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
        val dialogLayout = DialogMonthYearPickerBinding.inflate(LayoutInflater.from(context))

        val calendar = Calendar.getInstance()
        dialogLayout.monthPicker.run {
            wrapSelectorWheel = false
            minValue = 1
            maxValue = 12
            value = calendar.get(Calendar.MONTH)
            displayedValues = resources.getStringArray(R.array.ExpiryMonths)

            if (month != null && month in 1..12) {
                value = month!!
            }
        }

        dialogLayout.yearPicker.run {
            wrapSelectorWheel = false
            val y = calendar.get(Calendar.YEAR)
            minValue = y
            maxValue = y + 20
            value = y

            if (year != null && year in minValue..maxValue) {
                value = year!!
            }
        }

        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.form_card_expiry_dialog_title)
            .setView(dialogLayout.root)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.ok) { dialog, which ->
                month = dialogLayout.monthPicker.value
                year = dialogLayout.yearPicker.value
                binding.dateView.text = Utils.formatExpiryDate(month!!, year!!)
            }
            .create()
            .show()
    }
}