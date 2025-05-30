package com.example.composedemo.photopicker

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import coil.request.ImageRequest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoPickerScreen(
    viewModel: PhotoPickerViewModel = hiltViewModel(),
) {
    /*
 You can modify PickVisualMediaRequest if you want:

Only images ➔ PickVisualMedia.ImageOnly

Only videos ➔ PickVisualMedia.VideoOnly

Images + Videos ➔ PickVisualMedia.ImageAndVideo
    * */

    val context = LocalContext.current

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris ->
        if (!uris.isNullOrEmpty()) {
            viewModel.updateSelectedUris(uris)
            /*uris.forEach { uri ->
            val mediaType = getMediaType(context.contentResolver, uri)
                Log.d("mediaType", "Selected URI: ${mediaType.toString()}") // Log URI for debugging
            }*/
        }

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pick Images and Videos") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pick Multiple Images")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pick Multiple Videos")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                        )
                    } else {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pick Both (Images + Videos)")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Grid Listing
            if (viewModel.selectedUris.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(viewModel.selectedUris) { uri ->
                        val mediaType = getMediaType(context.contentResolver, uri)

                        val itemModifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.75f) // 4:3 ratio for nicer grid
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.LightGray)

                        if (mediaType == "Image") {
                            AsyncImage(
                                model = uri,
                                contentDescription = "Selected Image",
                                contentScale = ContentScale.Crop,
                                modifier = itemModifier
                            )
                        } else if (mediaType == "Video") {
                            val imageLoader = ImageLoader.Builder(context)
                                .components {
                                    add(VideoFrameDecoder.Factory())
                                }
                                .build()

                            val request = ImageRequest.Builder(context)
                                .data(uri)
                                .crossfade(true)
                                .build()

                            Box(
                                modifier = itemModifier
                            ) {
                                AsyncImage(
                                    model = request,
                                    imageLoader = imageLoader,
                                    contentDescription = "Video Thumbnail",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.matchParentSize()
                                )

                                Box(
                                    modifier = Modifier
                                        .matchParentSize()
                                        .background(Color.Black.copy(alpha = 0.4f))
                                )

                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "Play Button",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(64.dp)
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


fun getMediaType(contentResolver: ContentResolver, uri: Uri): String {
    var mimeType: String? = null

    // Query the URI to get the MIME type
    val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            val mimeTypeColumn = it.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE)
            if (mimeTypeColumn != -1) {
                mimeType = it.getString(mimeTypeColumn)
            }
        }
    }

    // Determine whether it’s an image or video based on MIME type
    return when {
        mimeType != null && mimeType!!.startsWith("image/") -> "Image"
        mimeType != null && mimeType!!.startsWith("video/") -> "Video"
        else -> "Unknown"
    }

}
