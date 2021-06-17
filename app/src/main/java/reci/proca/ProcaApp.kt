@file:Suppress("UnusedImport", "unused")

package reci.proca

import android.app.Application
import androidx.room.Room
import reci.proca.data.ProcaDB

class ProcaApp : Application() {

    // https://developer.android.com/training/data-storage/room#kotlin

    override fun onCreate() {
        super.onCreate()

        val builder = Room
            .inMemoryDatabaseBuilder(this, ProcaDB::class.java)
        //.databaseBuilder(this, AppDatabase::class.java, "ProcaDB")

        dbNullable = builder.build()
    }

    companion object {
        private var dbNullable: ProcaDB? = null

        val database: ProcaDB by lazy { dbNullable!! }
    }
}