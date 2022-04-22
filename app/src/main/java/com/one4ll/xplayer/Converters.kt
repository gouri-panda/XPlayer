package com.one4ll.xplayer

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.one4ll.xplayer.models.*

class Converters {
    @TypeConverter
    fun fromMedia(mediaList: List<Media>): String {
        val type = object : TypeToken<List<Media>>() {
        }.type
        return Gson().toJson(mediaList, type)
    }

    @TypeConverter
    fun toMedia(mediaListString: String): List<Media> {
        val type = object : TypeToken<List<Media>>() {
        }.type
        return Gson().fromJson(mediaListString, type)
    }
    @TypeConverter
    fun fromImage(imageList: List<Image>): String {
        val type = object : TypeToken<List<Image>>() {
        }.type
        return Gson().toJson(imageList, type)
    }

    @TypeConverter
    fun toImage(imageList: String): List<Image> {
        val type = object : TypeToken<List<Image>>() {
        }.type
        return Gson().fromJson(imageList, type)
    }
    @TypeConverter
    fun fromFavorite(favoriteList: List<Favorite>): String {
        val type = object : TypeToken<List<Favorite>>() {
        }.type
        return Gson().toJson(favoriteList, type)
    }

    @TypeConverter
    fun toFavorite(favoriteList: String): List<Favorite> {
        val type = object : TypeToken<List<Favorite>>() {
        }.type
        return Gson().fromJson(favoriteList, type)
    }
    @TypeConverter
    fun fromMusic(musicList: List<Music>): String {
        val type = object : TypeToken<List<Music>>() {
        }.type
        return Gson().toJson(musicList, type)
    }

    @TypeConverter
    fun toMusic(musicList: String): List<Music> {
        val type = object : TypeToken<List<Music>>() {
        }.type
        return Gson().fromJson(musicList, type)
    }
    @TypeConverter
    fun fromStreams(streamList: List<Streams>): String {
        val type = object : TypeToken<List<Streams>>() {
        }.type
        return Gson().toJson(streamList, type)
    }

    @TypeConverter
    fun toStreams(StreamList: String): List<Streams> {
        val type = object : TypeToken<List<Streams>>() {
        }.type
        return Gson().fromJson(StreamList, type)
    }
    @TypeConverter
    fun fromVideo(videoList: List<Video>): String {
        val type = object : TypeToken<List<Video>>() {
        }.type
        return Gson().toJson(videoList, type)
    }

    @TypeConverter
    fun toVideo(videoList: String): List<Video> {
        val type = object : TypeToken<List<Video>>() {
        }.type
        return Gson().fromJson(videoList, type)
    }
}