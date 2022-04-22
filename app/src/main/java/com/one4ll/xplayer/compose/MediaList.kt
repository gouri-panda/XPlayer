package com.one4ll.xplayer.compose

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.helpers.getBitmapFromVideoFile
import kotlinx.coroutines.*

@Composable
fun MediaList(
    modifier: Modifier = Modifier,
    items: List<Media>,
    onClickMediaItems: (Media) -> Unit,
    onClickMenuItem: (Media) -> Unit,
) {
    Surface(color = colorResource(id = R.color.white)) {
        LazyColumn(modifier = modifier) {
            for (item in items) {
                item {
                    MediaItem(
                        modifier,
                        mediaItem = item,
                        onClickMediaItems = onClickMediaItems,
                        onClickMenuItem = onClickMenuItem
                    )
                }
            }
        }
    }


}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MediaItem(
    modifier: Modifier = Modifier,
    mediaItem: Media,
    onClickMediaItems: (Media) -> Unit,
    onClickMenuItem: (Media) -> Unit,
) {
    Box {
        Row {
            Column(
                modifier = modifier
                    .padding(4.dp)
                    .weight(0.3F)
                    .clickable {
                        onClickMediaItems(mediaItem)
                    }
            ) {

                val localContext = LocalContext.current
//                val coroutineScope = rememberCoroutineScope()
                val thumbnail: MutableState<Bitmap?> = remember {
                    mutableStateOf(null)
                }
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context = localContext)
                        .data(thumbnail.value)
                        .placeholder(R.drawable.exo_controls_play)
                        .error(R.drawable.exo_controls_pause)
                        .build()
                )
                // TODO: Create thumbnail for each files
//                coroutineScope.launch {
//                    thumbnail.value = localContext.getBitmapFromVideoFile(mediaItem.path)
//                }

                if (painter.state is AsyncImagePainter.State.Loading) {
                    Image(
                        painter = painterResource(id = R.drawable.exo_controls_next),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Black)
                    )
                } else if (painter.state is AsyncImagePainter.State.Success) {
                    Image(
                        painter = painter,
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth()
                    )
                }


                MediaProgressBar(progress = 0.3F)
            }
            Column(modifier = Modifier.weight(0.6F)) {
                Text(
                    text = mediaItem.name,
                    modifier = Modifier
                        .padding(
                            top = 4.dp,
                            start = 8.dp,
                            end = 8.dp,
                            bottom = 4.dp
                        )
                        .clickable {
                            onClickMediaItems(mediaItem)
                        },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            ItemMenu(modifier = Modifier
                .weight(0.1F)
                .padding(end = 4.dp)
                .clickable {
                    onClickMenuItem(mediaItem)
                })
        }
    }
}

@Composable
private fun createBitmapFromPath(path: String): AsyncImagePainter {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context = context)
        .data(path)
        .apply {
            placeholder(R.drawable.exo_controls_play)
            crossfade(true)
            transformations()
        }
        .build()

    return rememberAsyncImagePainter(imageRequest)
}

@Composable
fun ItemMenu(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Rounded.MoreVert,
        contentDescription = "More option"
    )
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
    MediaList(
        items = items,
        modifier = Modifier.fillMaxWidth(),
        onClickMediaItems = {},
        onClickMenuItem = {}
    )
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
