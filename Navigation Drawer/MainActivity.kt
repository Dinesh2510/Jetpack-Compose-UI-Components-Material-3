package com.demo.jetpackcomposedemo

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardActions.Companion.Default

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardOptions.Companion.Default
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
                ContentNavDrawer()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "First View"
    )
    @Composable
    private fun ContentNavDrawer() {
        val activity = LocalContext.current as Activity
        val scope = rememberCoroutineScope()
        val drawerStates = rememberDrawerState(initialValue = DrawerValue.Closed)
        var selectedItems by remember {
            mutableStateOf("Home")
        }

        ModalNavigationDrawer(

            drawerContent = {
                ModalDrawerSheet(
                    Modifier
                        .width(300.dp)
                        .fillMaxHeight()

                ) {
                    Spacer(modifier = Modifier.height(40.dp))
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.image_10),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(
                                    CircleShape
                                )
                        )
                        Text(
                            text = "Pixel Developer",
                            Modifier.padding(top = 10.dp),
                            style = MaterialTheme.typography.labelMedium
                        )

                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    /*Items*/

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Home",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
                            )
                        },
                        selected = selectedItems == "Home",
                        onClick = {
                            scope.launch {
                                drawerStates.close()
                            }
                            selectedItems = "Home"
                        },
                        icon = {
                            Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Favorite",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
                            )
                        },
                        selected = selectedItems == "Favorite",
                        onClick = {
                            scope.launch {
                                drawerStates.close()
                            }
                            selectedItems = "Favorite"
                        },
                        icon = {
                            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Home")
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    Divider(
                        Modifier
                            .padding(horizontal = 25.dp)
                            .padding(top = 15.dp)
                    )

                    Text(
                        text = "Secure Tips",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W500),
                        modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Setting",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
                            )
                        },
                        selected = selectedItems == "Setting",
                        onClick = {
                            scope.launch {
                                drawerStates.close()
                            }
                            selectedItems = "Setting"
                        },
                        icon = {
                            Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Search",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W500)
                            )
                        },
                        selected = selectedItems == "Search",
                        onClick = {
                            scope.launch {
                                drawerStates.close()
                            }
                            selectedItems = "Search"
                        },
                        icon = {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )



                }


            },
            drawerState = drawerStates,
            content = {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    "Pixel Developer",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    if (drawerStates.isClosed) {
                                        scope.launch { drawerStates.open() }
                                    } else {
                                        scope.launch { drawerStates.close() }
                                    }
                                }) {
                                    Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                                }
                            },
                            actions = {
                                IconButton(onClick = { activity.finish() }) {
                                    Icon(imageVector = Icons.Default.Close, contentDescription = "")
                                }
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                            )

                        )

                    },
                    content = { innerpadding ->
                        Column(Modifier.padding(innerpadding)) {
                            //Dummy Content
                            IncludeContent.ContentMail()

                        }


                    }


                )


            }

        )


    }


}


