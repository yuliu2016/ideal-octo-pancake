package reci.proca.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ProcaDao {

    @Query("SELECT * FROM LOGENTRY WHERE refID = :currentRefID")
    fun findRelatedLogs(currentRefID: Int): List<LogEntry>
}