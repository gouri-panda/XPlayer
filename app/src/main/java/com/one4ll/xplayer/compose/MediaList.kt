package com.one4ll.xplayer.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R

@Composable
fun MediaList(modifier: Modifier = Modifier, items: List<Media>) {
    Surface(color = colorResource(id = R.color.white)) {
        LazyColumn(modifier = modifier) {
            for (item in items) {
                item {
                    MediaItem(
                        modifier,
                        item = item
                    )
                }
            }
        }
    }


}

@Composable
fun MediaItem(modifier: Modifier = Modifier, item: Media) {
    Box {
        Row {
            Column(
                modifier = modifier
                    .padding(4.dp)
                    .weight(0.3F)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.exo_icon_circular_play),
                    contentDescription = ""
                )
                MediaProgressBar(progress = 0.3F)
            }
            Column(modifier = Modifier.weight(0.6F)) {
                Text(
                    text = item.name,
                    modifier = Modifier.padding(
                        top = 4.dp,
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 4.dp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            ItemMenu(modifier = Modifier.weight(0.1F))
        }
    }
}

@Composable
fun ItemMenu(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "More option")
    }
}

@Composable
fun MediaProgressBar(
    modifier: Modifier = Modifier,
    progress: Float = 0.0F,
    backgroundColor: Color = Color.White,
    color: Color = Color.Red
) {
    LinearProgressIndicator(
        modifier = modifier,
        progress = progress,
        backgroundColor = backgroundColor,
        color = color
    )
}


@Preview
@PreviewParameter(MediaListPreviewParameterProvider::class)
@Composable
fun Prev_MediaList(@PreviewParameter(MediaListPreviewParameterProvider::class) items: List<Media>) {
    MediaList(items = items, modifier = Modifier.fillMaxWidth())
}

class MediaListPreviewParameterProvider : PreviewParameterProvider<List<Media>> {


    private val media1 = Media("item 1", "1.00", "", "")
    private val media2 = Media("item 2", "1.00", "", "")
    private val media3 = Media("item 3 is very big  preview for testing        ", "1.00", "", "")
    private val media4 = Media("item 4", "1.00", "", "")
    private val media5 = Media("item 5", "1.00", "", "")
    private val media6 = Media("item 6", "1.00", "", "")
    private val media7 = Media("item 7", "1.00", "", "")
    private val media8 = Media("item 8", "1.00", "", "")
    private val media9 = Media("item 9", "1.00", "", "")
    private val mediaList =
        sequenceOf(listOf(media1, media2, media3, media4, media5, media6, media7, media8, media9))

    override val values: Sequence<List<Media>>
        get() = mediaList
}

@Preview
@Composable
fun PreviewProgressBar() {
    MediaProgressBar(progress = 0.2F)
}