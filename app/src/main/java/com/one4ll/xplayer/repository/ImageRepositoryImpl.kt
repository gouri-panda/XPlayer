package com.one4ll.xplayer.repository

import com.one4ll.xplayer.database.MediaDatabase
import com.one4ll.xplayer.models.Image

class ImageRepositoryImpl(private val database: MediaDatabase) : ImageRepository {
    override fun getImages(): List<Image> {
        return database.imageDao().getAll()
    }

    override fun removeImages(image: Image) {
        database.imageDao().deleteImage(image)
    }

}