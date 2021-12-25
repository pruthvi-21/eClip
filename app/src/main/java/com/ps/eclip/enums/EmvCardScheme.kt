package com.ps.eclip.enums

import com.ps.eclip.R

enum class EmvCardScheme(
    val scheme: String,
    val iconRes: Int
) {
    AMEX("American Express", R.drawable.ic_amex),
    DINERS_CLUB("Diners Club", R.drawable.ic_dinersclub),
    DISCOVER("Discover", R.drawable.ic_discover),
    MAESTRO("Maestro", R.drawable.ic_maestro),
    MASTERCARD("Mastercard", R.drawable.ic_mastercard),
    RUPAY("Rupay", R.drawable.ic_rupay),
    VISA("Visa", R.drawable.ic_visa),
    OTHER("Other", R.drawable.ic_emv_icon)
}