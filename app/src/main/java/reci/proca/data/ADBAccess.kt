package reci.proca.data

import android.content.Context
import androidx.room.Room

object ADBAccess {

    // https://developer.android.com/training/data-storage/room#kotlin

    lateinit var db: AppDatabase

    fun initDB(applicationContext: Context) {

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }
}