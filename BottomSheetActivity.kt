package com.demo.jetpackcomposedemo

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardActions.Companion.Default

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardOptions.Companion.Default
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewClientCompat
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeAction.Companion.Search
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.webkit.WebViewRenderProcess
import androidx.compose.ui.unit.sp
import androidx.core.view.isVisible
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.webkit.WebResourceErrorCompat
import androidx.webkit.WebResourceRequestCompat
import androidx.webkit.WebViewFeature
import com.demo.jetpackcomposedemo.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ContentListingDemo()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "First View"
    )
    @Composable
    private fun ContentListingDemo() {
        val mContext = LocalContext.current
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberBottomSheetScaffoldState()


        fun openSheet() {
            scope.launch {
                scaffoldState.bottomSheetState.expand()
            }
        }

        fun closeSheet() {
            scope.launch {
                scaffoldState.bottomSheetState.partialExpand()
            }
        }

        BottomSheetScaffold(scaffoldState = scaffoldState,
            sheetPeekHeight = 0.dp,
            sheetShadowElevation = 20.dp,
            sheetContainerColor = MaterialTheme.colorScheme.surface,
            sheetContent = {
                Row {
                    Column(modifier = Modifier
                        .clickable { closeSheet() }
                        .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Outlined.Share, "")
                        Text(
                            text = "Share",
                            Modifier.padding(top = 10.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Column(modifier = Modifier
                        .clickable { closeSheet() }
                        .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Outlined.Add, "")
                        Text(
                            text = "Add to",
                            Modifier.padding(top = 10.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Column(modifier = Modifier
                        .clickable { closeSheet() }
                        .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Outlined.Favorite, "")
                        Text(
                            text = "Favorite",
                            Modifier.padding(top = 10.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Column(modifier = Modifier
                        .clickable { closeSheet() }
                        .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Outlined.MoreVert, "")
                        Text(
                            text = "More",
                            Modifier.padding(top = 10.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }


                }

                Divider(Modifier.padding(horizontal = 20.dp, vertical = 15.dp))

                Text(
                    text = "Send",
                    Modifier.padding(start = 20.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Row(
                    Modifier
                        .padding(horizontal = 20.dp)
                        .horizontalScroll(rememberScrollState()),
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable { closeSheet() }
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_1),
                            contentDescription = "",
                            contentScale = ContentScale.Crop, modifier = Modifier
                                .size(50.dp)
                                .clip(
                                    CircleShape
                                )
                        )
                        Text(
                            text = "Person 1",
                            modifier = Modifier.padding(top = 10.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable { closeSheet() }
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_2),
                            contentDescription = "",
                            contentScale = ContentScale.Crop, modifier = Modifier
                                .size(50.dp)
                                .clip(
                                    CircleShape
                                )
                        )
                        Text(
                            text = "Person 2",
                            modifier = Modifier.padding(top = 10.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable { closeSheet() }
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_3),
                            contentDescription = "",
                            contentScale = ContentScale.Crop, modifier = Modifier
                                .size(50.dp)
                                .clip(
                                    CircleShape
                                )
                        )
                        Text(
                            text = "Person 3",
                            modifier = Modifier.padding(top = 10.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable { closeSheet() }
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_4),
                            contentDescription = "",
                            contentScale = ContentScale.Crop, modifier = Modifier
                                .size(50.dp)
                                .clip(
                                    CircleShape
                                )
                        )
                        Text(
                            text = "Person 4",
                            modifier = Modifier.padding(top = 10.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable { closeSheet() }
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_5),
                            contentDescription = "",
                            contentScale = ContentScale.Crop, modifier = Modifier
                                .size(50.dp)
                                .clip(
                                    CircleShape
                                )
                        )
                        Text(
                            text = "Person 5",
                            modifier = Modifier.padding(top = 10.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable { closeSheet() }
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_6),
                            contentDescription = "",
                            contentScale = ContentScale.Crop, modifier = Modifier
                                .size(50.dp)
                                .clip(
                                    CircleShape
                                )
                        )
                        Text(
                            text = "Person 6",
                            modifier = Modifier.padding(top = 10.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                }

                Text(
                    text = "Add to album",
                    Modifier.padding(start = 20.dp, top = 15.dp, bottom = 10.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Row(
                    Modifier
                        .padding(horizontal = 10.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    Card(
                        modifier = Modifier
                            .size(150.dp)
                            .clickable { closeSheet() }
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_6),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Card(
                        modifier = Modifier
                            .size(150.dp)
                            .clickable { closeSheet() }
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_7),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Card(
                        modifier = Modifier
                            .size(150.dp)
                            .clickable { closeSheet() }
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_8),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Card(
                        modifier = Modifier
                            .size(150.dp)
                            .clickable { closeSheet() }
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_9),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Card(
                        modifier = Modifier
                            .size(150.dp)
                            .clickable { closeSheet() }
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_10),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Card(
                        modifier = Modifier
                            .size(150.dp)
                            .clickable { closeSheet() }
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.image_11),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }


                }
                Spacer(modifier = Modifier.height(25.dp))

            }) { innerpadding ->
            Column(modifier = Modifier.padding(innerpadding)) {
                LazyColumn {
                    val listingData = IncludeContent.getListHomeData(mContext)

                    items(listingData.size) {
                        val items = listingData[it]
                        //ui
                        Row(
                            Modifier
                                .padding(vertical = 10.dp, horizontal = 8.dp)
                                .clickable { openSheet() }, // onclick each item in list will open bottom sheet
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.width(10.dp))
                            Image(
                                painter = painterResource(id = items.image),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(50.dp)
                            )
                            Spacer(modifier = Modifier.width(18.dp))
                            Text(
                                text = items.title,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W500)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Default.MoreVert, contentDescription = "menu")
                            }


                        }
                        Divider(color = MaterialTheme.colorScheme.outlineVariant)
                    }
                }

            }

        }


    }
}

