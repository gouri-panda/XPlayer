package com.one4ll.xplayer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.one4ll.xplayer.interfaces.*
import com.one4ll.xplayer.models.*

@Database(entities = [Video::class, Image::class, Music::class, Streams::class, Favorite::class], version = 2)
abstract class MediaDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
    abstract fun imageDao(): ImageDao
    abstract fun musicDao(): MusicDao
    abstract fun streamsDao(): StreamsDao
    abstract fun favoritesDao(): FavoritesDao

    companion object {
        private var db: MediaDatabase? = null
        fun getInstance(context: Context): MediaDatabase {
            return db ?: synchronized(MediaDatabase::class) {
                return db
                        ?: Room.databaseBuilder(context, MediaDatabase::class.java, "mediaDatabase.db")
                                .fallbackToDestructiveMigration()
                                .build()
            }

        }

        fun destroyInstance() {
            db = null
        }
    }
}