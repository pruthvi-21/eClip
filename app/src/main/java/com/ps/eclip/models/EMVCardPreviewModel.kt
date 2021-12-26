package com.ps.eclip.models

import androidx.room.*
import com.ps.eclip.enums.EMVCardType
import com.ps.eclip.utils.Converters

@Entity(
    tableName = "bank_card_preview",
    foreignKeys = [ForeignKey(
        entity = EMVCard::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onDelete = ForeignKey.CASCADE
    )]
)
@TypeConverters(Converters::class)
data class EMVCardPreviewModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "card_number")
    var cardNumber: Long?,

    @ColumnInfo(name = "card_type")
    var cardType: EMVCardType,

    @ColumnInfo(name = "card_label")
    var cardLabel: String
)