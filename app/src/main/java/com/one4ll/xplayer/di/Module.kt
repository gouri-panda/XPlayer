package com.one4ll.xplayer.di

import android.app.Application
import android.content.Context
import android.media.AudioManager
import androidx.room.Room
import com.google.android.exoplayer2.SimpleExoPlayer
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

    @Provides
    @Singleton
    fun provideSimpleExoPlayer(app: Application): SimpleExoPlayer =
        SimpleExoPlayer.Builder(app).build()

    @Provides
    @Singleton
    fun provideAudioManager(app: Application) =
        app.getSystemService(Context.AUDIO_SERVICE) as AudioManager
}