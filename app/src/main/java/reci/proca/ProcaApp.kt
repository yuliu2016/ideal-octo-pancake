@file:Suppress("UnusedImport", "unused")

package reci.proca

import android.app.Application
import android.content.Context
import androidx.room.Room
import reci.proca.data.ProcaDB

class ProcaApp : Application() {

    // https://developer.android.com/training/data-storage/room#kotlin

    override fun onCreate() {
        super.onCreate()
        database(this, inMemory = true)
    }

    companion object {
        private var procaDB: ProcaDB? = null

        fun database(context: Context, inMemory: Boolean = true): ProcaDB {
            val db = procaDB
            if (db != null) {
                return db
            }
            val builder = if (inMemory) {
                Room.inMemoryDatabaseBuilder(context, ProcaDB::class.java)
            } else {
                Room.databaseBuilder(context, ProcaDB::class.java, "ProcaDB")
            }

            val newDB = builder.build()
            procaDB = newDB
            return newDB
        }
    }
}