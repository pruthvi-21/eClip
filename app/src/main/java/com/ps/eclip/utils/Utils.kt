package com.ps.eclip.utils

import android.content.Context
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.view.Display
import androidx.core.content.getSystemService
import com.ps.eclip.enums.EmvCardScheme
import java.text.DecimalFormat

object Utils {
    private const val TAG = "Utils"

    fun formatCardNumber(num: Long?): String? {
        num ?: return null

        val scheme = CardSchemeIdentifier.match(num.toString())
        val formatter = DecimalFormat()

        when (scheme) {
            EmvCardScheme.AMEX -> formatter.applyPattern(AMEX_FORMAT)
            EmvCardScheme.MAESTRO -> {
                val l = num.toString().length
                formatter.applyPattern(
                    when (l) {
                        13 -> MAESTRO_13_FORMAT
                        15 -> MAESTRO_15_FORMAT
                        16 -> MAESTRO_16_FORMAT
                        19 -> MAESTRO_19_FORMAT
                        else -> ""
                    }
                )
            }
            EmvCardScheme.DINERS_CLUB -> formatter.applyPattern(DINERSCLUB_FORMAT)
            EmvCardScheme.MASTERCARD -> formatter.applyPattern(MASTERCARD_FORMAT)
            EmvCardScheme.DISCOVER -> formatter.applyPattern(DISCOVER_FORMAT)
            EmvCardScheme.RUPAY -> formatter.applyPattern(RUPAY_FORMAT)
            EmvCardScheme.VISA -> formatter.applyPattern(VISA_FORMAT)
            else -> return num.toString()
        }
        return formatter.format(num).replace(',', ' ')
    }

    fun formatCardNumberPreview(num: Long?): String? {
        num ?: return null
        val str = num.toString()
        if (str.length == 1) return "."
        if (str.length == 2) return ".."
        if (str.length == 3) return "..."
        if (str.length == 4) return "...."
        return ".. " + str.substring(str.length - 4, str.length)
    }

    fun formatExpiryDate(month: Int, year: Int): String {
        return "${String.format("%02d", month)}/${year % 100}"
    }

    fun getScreenSize(context: Context): IntArray {
        val screenSize = Point()
        context.getSystemService<DisplayManager>()!! //DisplayManager
            .getDisplay(Display.DEFAULT_DISPLAY) //Display
            .getRealSize(screenSize)
        return intArrayOf(screenSize.x, screenSize.y)
    }

    private const val AMEX_FORMAT = "####,######,#####" //4-6-5
    private const val VISA_FORMAT = "####,####,####,####" //4-4-4-4
    private const val MASTERCARD_FORMAT = "####,####,####,####" //4-4-4-4
    private const val DISCOVER_FORMAT = "####,####,####,####" //4-4-4-4
    private const val RUPAY_FORMAT = DISCOVER_FORMAT
    private const val DINERSCLUB_FORMAT = "####,######,####" //4-6-4
    private const val MAESTRO_13_FORMAT = "####,####,#####" //4-4-5
    private const val MAESTRO_15_FORMAT = "####,######,#####" //4-6-5
    private const val MAESTRO_16_FORMAT = "####,####,####,####" //4-4-4-4
    private const val MAESTRO_19_FORMAT = "####,####,####,####,###" //4-4-4-4-3
}