package com.one4ll.xplayer.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.one4ll.xplayer.R

@Composable
fun FoldersList(
    modifier: Modifier = Modifier,
    folders: List<String>,
    onClick: (String) -> Unit
) {
    Surface {
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
                                    painter = painterResource(id = R.drawable.ic_baseline_folder_24),
                                    contentDescription = "",
                                    modifier = Modifier.size(width = 24.dp, height = 24.dp)
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
    val fakeFolderList = listOf("Camera", "Download", "Internal memory", "New folder", "Recordings")
    FoldersList(
        folders = fakeFolderList,
        onClick = {},
        modifier = Modifier
    )
}