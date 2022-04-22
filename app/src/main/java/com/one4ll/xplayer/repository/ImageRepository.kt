package com.one4ll.xplayer.repository

import com.one4ll.xplayer.database.MediaDatabase
import com.one4ll.xplayer.models.Image

interface ImageRepository {
    fun getImages(): List<Image>
    fun removeImages(image: Image)
}
