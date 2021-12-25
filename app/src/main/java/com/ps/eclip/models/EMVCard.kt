package com.ps.eclip.models

import androidx.room.*
import com.ps.eclip.enums.EMVCardType
import com.ps.eclip.utils.Converters

@Entity(tableName = "bank_card")
@TypeConverters(Converters::class)
class EMVCard {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "card_number")
    var cardNumber: Long?

    @ColumnInfo(name = "card_holder_name")
    var cardHolder: String?

    @ColumnInfo(name = "expiry_month")
    var expiryMonth: Int?

    @ColumnInfo(name = "expiry_year")
    var expiryYear: Int?

    @ColumnInfo(name = "cvv")
    var cvv: Int?

    @ColumnInfo(name = "card_type")
    var cardType: EMVCardType

    @ColumnInfo(name = "card_label")
    var cardLabel: String

    constructor(
        id: Int = -1,
        cardNumber: Long?,
        expiryMonth: Int?,
        expiryYear: Int?,
        cvv: Int?,
        cardType: EMVCardType,
        cardHolder: String?,
        cardLabel: String
    ) {
        this.id = id
        this.cardNumber = cardNumber
        this.expiryMonth = expiryMonth
        this.expiryYear = expiryYear
        this.cvv = cvv
        this.cardType = cardType
        this.cardHolder = cardHolder
        this.cardLabel = cardLabel
    }

    @Ignore
    constructor(
        cardNumber: Long?,
        expiryMonth: Int?,
        expiryYear: Int?,
        cvv: Int?,
        cardType: EMVCardType,
        cardHolder: String?,
        cardLabel: String
    ) {
        this.cardNumber = cardNumber
        this.expiryMonth = expiryMonth
        this.expiryYear = expiryYear
        this.cvv = cvv
        this.cardType = cardType
        this.cardHolder = cardHolder
        this.cardLabel = cardLabel
    }
}