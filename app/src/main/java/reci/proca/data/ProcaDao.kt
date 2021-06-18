package reci.proca.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ProcaDao {

    @Query("SELECT * FROM LOGENTRY WHERE refID = :currentRefID")
    suspend fun findRelatedLogs(currentRefID: Int): List<LogEntry>

    @Transaction
    @Query("SELECT * FROM LOGENTRY ORDER BY timestamp DESC limit :limit offset :offset")
    suspend fun recentEntries(limit: Int, offset: Int): List<CompositeLog>


}