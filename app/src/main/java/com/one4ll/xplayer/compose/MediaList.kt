package com.one4ll.xplayer.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.one4ll.xplayer.Media

@Composable
fun MediaList(modifier: Modifier = Modifier, items: List<Media>) {

    LazyColumn(modifier = modifier) {
        for (i in items) {
            item {
                MediaItem(
                    modifier,
                    items = items
                )
            }
        }
    }

}

@Composable
fun MediaItem(modifier: Modifier = Modifier, items: List<Media>) {

}


@Preview
@Composable
fun Prev_MediaList() {
}