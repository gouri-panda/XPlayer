package com.one4ll.xplayer

import com.one4ll.xplayer.helpers.convertDuration
import com.one4ll.xplayer.helpers.getTitleFromVideoPath
import org.junit.Test
import org.junit.Assert.*


class UtilTest {

    @Test
    fun `check title video path from string`() {
        val path = "android/emulated/0/download/amazing_video.mp4"
        val title = path.getTitleFromVideoPath()
        assertEquals("amazing_video", title)
    }

    @Test
    fun `check title video single path from string`() {
        val path = "/funny_cat_video.mp4"
        val title = path.getTitleFromVideoPath()
        assertEquals("funny_cat_video", title)
    }

    @Test
    fun `check title not found if the extension of file name doesn't  exist`() {
        val path = "android/emulated/0/download/amazing_video"
        val title = path.getTitleFromVideoPath()
        assertEquals("", title)
    }

    @Test
    fun `check convert duration from long to string`() {
        var duration = 3600000L
        var stringDuration = convertDuration(duration)
        assertEquals("1:00:00", stringDuration)
        duration = 3660000L
        stringDuration = convertDuration(duration)
        assertEquals("1:1:00", stringDuration)
        duration = 3720000L
        stringDuration = convertDuration(duration)
        assertEquals("1:2:00", stringDuration)
    }


}