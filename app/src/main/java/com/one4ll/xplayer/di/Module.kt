package com.one4ll.xplayer.di

import android.app.Application
import androidx.room.Room
import com.one4ll.xplayer.database.MediaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideDataBase(app: Application): MediaDatabase {
        return Room.databaseBuilder(
            app,
            MediaDatabase::class.java,
            MediaDatabase.DATABASE_NAME
        ).build()
    }
}