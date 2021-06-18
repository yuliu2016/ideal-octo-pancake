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

    // https://stackoverflow.com/questions/44322178/room-schema-export-directory-is-not-provided-to-the-annotation-processor-so-we
    exportSchema = false,
)
abstract class ProcaDB : RoomDatabase() {
    abstract fun procaDao(): ProcaDao
}