package com.ps.eclip.utils

import android.content.Context
import android.nfc.NfcAdapter

object NfcUtils {
    private const val TAG = "NfcUtils"

    fun isNfcAvailable(context: Context): Boolean {
        return NfcAdapter.getDefaultAdapter(context) != null
    }
}