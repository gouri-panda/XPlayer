package com.one4ll.xplayer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.one4ll.xplayer.Converters
import com.one4ll.xplayer.interfaces.*
import com.one4ll.xplayer.models.*

@Database(
    entities = [Video::class, Image::class, Music::class, Streams::class, Favorite::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MediaDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
    abstract fun imageDao(): ImageDao
    abstract fun musicDao(): MusicDao
    abstract fun streamsDao(): StreamsDao
    abstract fun favoritesDao(): FavoritesDao

    companion object {
        const val DATABASE_NAME = "mediaDatabase.db"
    }
}