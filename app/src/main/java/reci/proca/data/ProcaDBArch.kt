@file:Suppress("unused", "ArrayInDataClass")

package reci.proca.data

import androidx.room.*

/*
 * The database design architecture
 */

@Entity
data class Step(
    @PrimaryKey val stepID: Int,
    val description: String,
    val annotation: String,
    val pictureID: Int
)


@Entity
data class Picture(
    @PrimaryKey val pictureID: Int,
    val fullFile: String,
    val thumbFile: String,
    val timestamp: Int,
    val smallThumb: ByteArray
)

@Entity
data class LogEntry(
    @PrimaryKey val entryID: Int,
    val timestamp: Int,
    val modified: Int,
    val recipeID: Int
)

@Entity
data class LogEntryMemo(
    @PrimaryKey val memoID: Int,
    val logID: Int,
    val rating: Int,
    val description: String,
)

data class LogWithMemo(
    @Embedded val entry: LogEntry,
    @Relation(
        parentColumn = "entryID",
        entityColumn = "logID"
    )
    val memo: LogEntryMemo
)

