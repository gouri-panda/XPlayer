package com.one4ll.xplayer.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign


//@Composable
//fun MediaCard(
//    @PreviewParameter(FakeMedia::class) media: Media,
//    thumbNailLoad: (String) -> Bitmap?
//) {
////todo add transitionname function of XML
//    Surface {
//        Row {
//            Column {
//                Image(
//                    painter = rememberCoilPainter(
//                        request = thumbNailLoad(media.path),
//                        previewPlaceholder = R.drawable.placeholder
//                    ),
//                    contentDescription = null,
//                    modifier = Modifier.size(140.dp, 100.dp)
//                )
//                LengthCardView(
//                    length = media.duration
//                )
//                LinearProgressIndicator(
//                    progress = media.watched,
//                    color = Color.Red
//                )
//            }
//            Text(text = media.name, Modifier.padding(PaddingValues(16.dp)))
//            FileOptions()
//        }
//    }
//}

@Composable
fun LengthCardView(
    length: String
) {
    Text(text = length, color = Color.White, textAlign = TextAlign.End)
}

//@Composable
//fun FileOptions() {
//    Image(
//        painter = painterResource(id = R.drawable.ic_more_vert_48px),
//        contentDescription = null,
//        modifier = Modifier
//            .padding(PaddingValues(end = 4.dp))
//            .size(32.dp, 32.dp)
//
//    )
//}


