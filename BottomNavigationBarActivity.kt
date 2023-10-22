package com.app.myapplication


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import com.app.myapplication.ui.theme.AppTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class BottomNavigationActivity : androidx.activity.ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ContentNavigationBarBasic()
            }
        }
    }

    @ExperimentalMaterial3Api
    @ExperimentalLayoutApi
    @Preview(showBackground = true)
    @Composable
    fun ContentNavigationBarBasic() {
        var selectedItem by remember { mutableStateOf("Mail") }
        Scaffold(
            topBar = {
                BasicTopAppBar("Navigation Bar")
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
                ) {
                    IncludeContent.getListingContent()
                }
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        icon = {
                            Icon(
                                ImageVector.vectorResource(id = R.drawable.ic_Mail),
                                contentDescription = ""
                            )
                        },
                        label = { Text("Mail") },
                        selected = selectedItem == "Mail",
                        onClick = { selectedItem = "Mail" }
                    )
                    NavigationBarItem(
                        icon = {
                            Icon(
                                ImageVector.vectorResource(id = R.drawable.ic_chat),
                                contentDescription = ""
                            )
                        },
                        label = { Text("Chat") },
                        selected = selectedItem == "Chat",
                        onClick = { selectedItem = "Chat" }
                    )
                    NavigationBarItem(
                        icon = {
                            Icon(
                                ImageVector.vectorResource(id = R.drawable.ic_group),
                                contentDescription = ""
                            )
                        },
                        label = { Text("Room") },
                        selected = selectedItem == "Room",
                        onClick = { selectedItem = "Room" }
                    )
                    NavigationBarItem(
                        icon = {
                            Icon(
                                ImageVector.vectorResource(id = R.drawable.ic_video),
                                contentDescription = ""
                            )
                        },
                        label = { Text("Meet") },
                        selected = selectedItem == "Meet",
                        onClick = { selectedItem = "Meet" }
                    )
                }
            }
        )

    }

}