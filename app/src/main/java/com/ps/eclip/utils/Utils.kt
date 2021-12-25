package com.ps.eclip.utils

import android.content.Context
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.view.Display
import androidx.core.content.getSystemService

object Utils {
    private const val TAG = "Utils"

    fun formatCardNumber(num: Long?): String? {
        num ?: return null
        val str = num.toString()
        if (str.length < 16) return str
        return StringBuilder(str)
            .insert(4, " ")
            .insert(9, " ")
            .insert(14, " ")
            .toString()
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
}