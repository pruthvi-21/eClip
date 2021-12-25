package com.ps.eclip.utils

import androidx.room.TypeConverter
import com.ps.eclip.enums.EMVCardType

class Converters {

    @TypeConverter
    fun toBankCardType(value: String) = enumValueOf<EMVCardType>(value)

    @TypeConverter
    fun fromBankCardType(value: EMVCardType) = value.name
}