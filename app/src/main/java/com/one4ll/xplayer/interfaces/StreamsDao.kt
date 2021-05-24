package com.one4ll.xplayer.interfaces

import androidx.room.*
import com.one4ll.xplayer.models.Streams
import kotlinx.coroutines.flow.Flow

@Dao
interface StreamsDao {
    @Insert
    suspend fun insert(stream: Streams)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(stream: Streams)

    @Query("SELECT * FROM streams ORDER BY time DESC")
    fun getAllByTime(): Flow<List<Streams>>

    @Query("DELETE  FROM streams")
    suspend fun removeAll()

    @Query("DELETE  FROM streams WHERE id = :id")
    suspend fun removeById(id: Long)

    @Query("SELECT * FROM streams WHERE time = :time")
    fun getValueBYId(time: Long): Flow<List<Streams>>

    @Query("UPDATE streams SET time = :time WHERE id = :id")
    suspend fun updateTime(id: Long, time: Long)
}