package com.ps.eclip.dao

import androidx.room.*
import com.ps.eclip.models.EMVCard

@Dao
interface EMVCardDao {
    @Query("SELECT * FROM bank_card ORDER BY id")
    fun loadAllCards(): List<EMVCard>?

    @Insert
    fun insertCard(card: EMVCard)

    @Update
    fun updateCard(card: EMVCard)

    @Delete
    fun removeCard(card: EMVCard)

    @Query("SELECT * FROM bank_card WHERE id = :id")
    fun getInfoWithId(id: Int?): EMVCard
}