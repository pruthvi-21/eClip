package com.ps.eclip.dao

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ps.eclip.models.EMVCard
import com.ps.eclip.models.EMVCardPreviewModel

@Database(entities = [EMVCard::class, EMVCardPreviewModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emvCardDao(): EMVCardDao?
    abstract fun emvCardPreviewDao(): EMVCardPreviewDao?

    companion object {
        private const val LOG = "AppDatabase"
        private const val DATABASE_NAME = "cards"

        private var sInstance: AppDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    Log.d(LOG, "Creating new database instance")
                    sInstance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            Log.d(LOG, "Getting the database instance")
            return sInstance
        }
    }
}