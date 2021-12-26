package com.ps.eclip.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ps.eclip.enums.EMVCardType
import com.ps.eclip.utils.Converters

@Entity(tableName = "bank_card")
@TypeConverters(Converters::class)
data class EMVCard(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "card_number")
    var cardNumber: Long?,

    @ColumnInfo(name = "card_holder_name")
    var cardHolder: String?,

    @ColumnInfo(name = "expiry_month")
    var expiryMonth: Int?,

    @ColumnInfo(name = "expiry_year")
    var expiryYear: Int?,

    @ColumnInfo(name = "cvv")
    var cvv: Int?,

    @ColumnInfo(name = "card_type")
    var cardType: EMVCardType,

    @ColumnInfo(name = "card_label")
    var cardLabel: String
)