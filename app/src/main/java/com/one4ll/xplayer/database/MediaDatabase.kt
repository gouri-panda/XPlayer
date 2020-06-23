package com.one4ll.xplayer.database

import android.content.Context
import androidx.room.*
import com.one4ll.xplayer.interfaces.MediumDao
import com.one4ll.xplayer.models.Medium

@Database(entities = [Medium::class],version = 1)
abstract class MediaDatabase : RoomDatabase() {
    abstract fun mediumDao() : MediumDao

    companion object {
        private var db: MediaDatabase? = null
        fun getInstance(context: Context): MediaDatabase {
            if (db == null) {
                synchronized(MediaDatabase::class) {
                    db = Room.databaseBuilder(context, MediaDatabase::class.java, "mediaDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return db!!

        }

        fun destroyInstance() {
            db = null
        }
    }
}