package com.ps.eclip.enums

import com.ps.eclip.R

enum class EmvCardScheme(
    val scheme: String,
    val iconRes: Int
) {
    VISA("Visa", R.drawable.ic_visa),
    MASTERCARD("Mastercard", R.drawable.ic_mastercard),
    RUPAY("Rupay", R.drawable.ic_rupay)
}