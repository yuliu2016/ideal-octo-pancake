package reci.proca.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Step::class,
        Picture::class,
        LogEntry::class,
        LogEntryMemo::class,
    ],
    version = 1
)
abstract class ProcaDB : RoomDatabase() {
    abstract fun procaDao(): ProcaDao
}