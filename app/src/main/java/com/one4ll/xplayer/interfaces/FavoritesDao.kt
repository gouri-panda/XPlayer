package com.one4ll.xplayer.interfaces

import androidx.room.*
import com.one4ll.xplayer.models.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateFavorites(favorite: Favorite)

    @Insert
    fun insertFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite")
    fun removeAll()

    @Query("SELECT * FROM favorite")
    fun selectAll(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite ORDER BY time DESC")
    fun selectAllByTimeInDesc(): Flow<List<Favorite>>

    @Query("DELETE FROM favorite WHERE id = :id")
    fun removeById(id: Long)

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun getAllById(id: Long): Flow<Favorite>
}