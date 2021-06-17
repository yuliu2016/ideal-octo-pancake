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
data class LogEntry(
    @PrimaryKey val entryID: Int,
    val timestamp: Int,
    val modified: Int,
    val isDraft: Boolean,
    val referenceID: Int,
    val selfRating: Int,
    val annotation: String,
)


@Entity
data class ReferenceEntry(
    @PrimaryKey val refID: Int,

    )
