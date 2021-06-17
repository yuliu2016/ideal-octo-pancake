@file:Suppress("UnusedImport", "unused")

package reci.proca

import android.app.Application
import androidx.room.Room
import reci.proca.data.AppDatabase

class ProcaApp : Application() {

    // https://developer.android.com/training/data-storage/room#kotlin

    override fun onCreate() {
        super.onCreate()

        val builder = Room
            .inMemoryDatabaseBuilder(this, AppDatabase::class.java)
        //.databaseBuilder(this, AppDatabase::class.java, "ProcaDB")

        dbNullable = builder.build()
    }

    companion object {
        private var dbNullable: AppDatabase? = null

        val database: AppDatabase by lazy { dbNullable!! }
    }
}