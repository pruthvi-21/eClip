package com.ps.eclip.utils

import com.ps.eclip.enums.EmvCardScheme

object CardSchemeValidator {

    fun validate(num: String?): EmvCardScheme {
        num ?: return EmvCardScheme.OTHER
        if (num.matches(RUPAY_REGEX)) return EmvCardScheme.RUPAY

        if (num.matches(MAESTRO_REGEX)) return EmvCardScheme.MAESTRO

        if (num.matches(DINERS_CLUB_REGEX)) return EmvCardScheme.DINERS_CLUB

        if (num.matches(VISA_REGEX)) return EmvCardScheme.VISA

        if (num.matches(MASTERCARD_REGEX)) return EmvCardScheme.MASTERCARD

        if (num.matches(AMEX_REGEX)) return EmvCardScheme.AMEX

        if (num.matches(DISCOVER_REGEX)) return EmvCardScheme.DISCOVER

        return EmvCardScheme.OTHER
    }

    private const val AMEX_PATTERN = "^3[47][0-9]{13}\$"
    private const val DINERS_CLUB_PATTERN = "^3(?:0[0-5]|[68][0-9])[0-9]{11}\$"
    private const val DISCOVER_PATTERN =
        "^65[4-9][0-9]{13}|64[4-9][0-9]{13}|6011[0-9]{12}|(622(?:12[6-9]|1[3-9][0-9]|[2-8][0-9][0-9]|9[01][0-9]|92[0-5])[0-9]{10})\$"
    private const val MAESTRO_PATTERN = "^(5018|5020|5038|5893|6304|6759|6761|6762|6763)[0-9]{8,15}\$"
    private const val MASTERCARD_PATTERN =
        "^(5[1-5][0-9]{14}|2(22[1-9][0-9]{12}|2[3-9][0-9]{13}|[3-6][0-9]{14}|7[0-1][0-9]{13}|720[0-9]{12}))\$"
    private const val RUPAY_PATTERN = "^6(?!011)(?:0[0-9]{14}|52[12][0-9]{12})\$"
    private const val VISA_PATTERN = "^4[0-9]{12}(?:[0-9]{3})?\$"

    private val AMEX_REGEX = AMEX_PATTERN.toRegex()
    private val DINERS_CLUB_REGEX = DINERS_CLUB_PATTERN.toRegex()
    private val DISCOVER_REGEX = DISCOVER_PATTERN.toRegex()
    private val MAESTRO_REGEX = MAESTRO_PATTERN.toRegex()
    private val MASTERCARD_REGEX = MASTERCARD_PATTERN.toRegex()
    private val RUPAY_REGEX = RUPAY_PATTERN.toRegex()
    private val VISA_REGEX = VISA_PATTERN.toRegex()
}