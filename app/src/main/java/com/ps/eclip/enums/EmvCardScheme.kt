package com.ps.eclip.enums

import com.ps.eclip.R

enum class EmvCardScheme(
    val scheme: String,
    val iconRes: Int,
    val logoRes: Int
) {
    AMEX("American Express", R.drawable.ic_amex, R.drawable.ic_amex_logo),
    DINERS_CLUB("Diners Club", R.drawable.ic_dinersclub, R.drawable.ic_dinersclub_logo),
    DISCOVER("Discover", R.drawable.ic_discover, R.drawable.ic_discover_logo),
    MAESTRO("Maestro", R.drawable.ic_maestro, R.drawable.ic_maestro_logo),
    MASTERCARD("Mastercard", R.drawable.ic_mastercard, R.drawable.ic_mastercard_logo),
    RUPAY("Rupay", R.drawable.ic_rupay, R.drawable.ic_rupay_logo),
    VISA("Visa", R.drawable.ic_visa, R.drawable.ic_visa_logo),
    OTHER("Other", R.drawable.ic_emv_icon, 0)
}