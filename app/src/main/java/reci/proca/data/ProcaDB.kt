package reci.proca.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Step::class,
        Picture::class,
        ReferenceEntry::class,
        LogEntry::class,
        EntryPicture::class
    ],
    version = 1,
    exportSchema = false,
)
abstract class ProcaDB : RoomDatabase() {
    abstract fun procaDao(): ProcaDao
}