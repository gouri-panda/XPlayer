package com.one4ll.xplayer.compose

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.one4ll.xplayer.FakeMedia
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R


@Composable
fun MediaCard(
    @PreviewParameter(FakeMedia::class) media: Media,
    thumbNailLoad: (String) -> Bitmap?
) {
//todo add transitionname function of XML
    Surface {
        Row {
            Column {
                Image(
                    painter = rememberCoilPainter(
                        request = thumbNailLoad(media.path),
                        previewPlaceholder = R.drawable.placeholder
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(140.dp, 100.dp)
                )
                LengthCardView(
                    length = media.duration
                )
                LinearProgressIndicator(
                    progress = media.watched,
                    color = Color.Red
                )
            }
            Text(text = media.name, Modifier.padding(PaddingValues(16.dp)))
            FileOptions()
        }
    }
}

@Composable
fun LengthCardView(
    length: String
) {
    Text(text = length, color = Color.White, textAlign = TextAlign.End)
}

@Composable
fun FileOptions() {
    Image(
        painter = painterResource(id = R.drawable.ic_more_vert_48px),
        contentDescription = null,
        modifier = Modifier
            .padding(PaddingValues(end = 4.dp))
            .size(32.dp, 32.dp)

    )
}


