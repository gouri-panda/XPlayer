package com.one4ll.xplayer.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.one4ll.xplayer.R

@Composable
fun FolderList(
    modifier: Modifier = Modifier,
    folders: List<String>,
    onClick: (String) -> Unit
) {
    Surface(color = colorResource(id = R.color.white)) {
        LazyColumn(modifier = modifier) {
            for (folder in folders) {
                item {
                    Box {
                        Row {
                            Column(
                                modifier = modifier
                                    .padding(4.dp)
                                    .clickable {
                                        onClick(folder)
                                    }
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.exo_icon_circular_play),
                                    contentDescription = "",
                                )
                            }
                            Column {
                                Text(
                                    text = folder,
                                    modifier = Modifier
                                        .padding(
                                            top = 4.dp,
                                            start = 8.dp,
                                            end = 8.dp,
                                            bottom = 4.dp
                                        )
                                        .clickable {
                                            onClick(folder)
                                        },
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}


@Preview
@Composable
fun Preview_FolderList() {
    val fakeFolderList = listOf("one", "two", "three", "four", "five")
    FolderList(folders = fakeFolderList, onClick = {})
}