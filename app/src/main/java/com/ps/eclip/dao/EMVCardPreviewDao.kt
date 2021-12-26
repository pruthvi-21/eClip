package com.ps.eclip.dao

import androidx.room.*
import com.ps.eclip.models.EMVCard
import com.ps.eclip.models.EMVCardPreviewModel

@Dao
interface EMVCardPreviewDao {
    @Query("SELECT * FROM bank_card_preview ORDER BY id")
    fun loadAllPreviews(): List<EMVCardPreviewModel>?

    @Insert
    fun insertPreview(card: EMVCardPreviewModel)

    @Update
    fun updatePreview(card: EMVCardPreviewModel)

    @Delete
    fun removePreview(card: EMVCardPreviewModel)
}