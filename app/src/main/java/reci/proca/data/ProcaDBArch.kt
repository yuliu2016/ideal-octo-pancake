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
    val pictureID: Int,
    val entryID: Int,
    val isReference: Boolean
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
data class ReferenceEntry(
    @PrimaryKey val refID: Int,
    val modified: Int,
    val avgRating: Int,
    val annotation: String,
    val logNum: Int
)


@Entity
data class LogEntry(
    @PrimaryKey val entryID: Int,
    val timestamp: Int,
    val modified: Int,
    val isDraft: Boolean,
    val refID: Int,
    val selfRating: Int,
    val annotation: String,
    val variations: Int,
)


/**
 * Each entry (whether reference or not)
 * has a list of associated pictures
 */
@Entity
data class EntryPicture(
    @PrimaryKey val entryID: Int,
    val pictureID: Int,
    val isReference: Boolean
)


data class CompositeLog(
    @Embedded val entry: LogEntry,
    @Relation(
        parentColumn = "entryID",
        entityColumn = "entryID"
    )
    val steps: List<Step>,
    val pictures: List<EntryPicture>
)