package com.ps.eclip.utils

import android.content.Context
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.view.Display
import androidx.core.content.getSystemService

object Utils {
    private const val TAG = "Utils"

    fun getScreenSize(context: Context): IntArray {
        val screenSize = Point()
        context.getSystemService<DisplayManager>()!! //DisplayManager
            .getDisplay(Display.DEFAULT_DISPLAY) //Display
            .getRealSize(screenSize)
        return intArrayOf(screenSize.x, screenSize.y)
    }
}