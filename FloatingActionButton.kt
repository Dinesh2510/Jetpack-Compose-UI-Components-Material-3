package com.demo.jetpackcomposedemo

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.demo.jetpackcomposedemo.ui.theme.AppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
               AnimatedExtendedFloatingActionButtonSample()
            }
        }
    }


  

    @Preview
    @Composable
    fun FloatingActionButtonSample() {
        FloatingActionButton(
            onClick = { /* do something */ },
        ) {
            Icon(Icons.Filled.Add, "Localized description")
        }
    }

    @Preview

    @Composable
    fun SmallFloatingActionButtonSample() {
        SmallFloatingActionButton(
            onClick = { /* do something */ },
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Localized description")
        }
    }

    @Preview

    @Composable
    fun LargeFloatingActionButtonSample() {
        LargeFloatingActionButton(
            onClick = { /* do something */ },
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Localized description",
                modifier = Modifier.size(FloatingActionButtonDefaults.LargeIconSize),
            )
        }
    }

    @Preview

    @Composable
    fun ExtendedFloatingActionButtonTextSample() {
        ExtendedFloatingActionButton(onClick = { /* do something */ }) {
            Text(text = "Extended FAB")
        }
    }

    @Preview

    @Composable
    fun ExtendedFloatingActionButtonSample() {
        ExtendedFloatingActionButton(
            onClick = { /* do something */ },
            icon = { Icon(Icons.Filled.Add, "Localized description") },
            text = { Text(text = "Extended FAB") },
        )
    }

    @Preview

    @Composable
    fun AnimatedExtendedFloatingActionButtonSample() {
        val listState = rememberLazyListState()
        // The FAB is initially expanded. Once the first visible item is past the first item we
        // collapse the FAB. We use a remembered derived state to minimize unnecessary compositions.
        val expandedFab by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex == 0
            }
        }
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = { /* do something */ },
                    expanded = expandedFab,
                    icon = { Icon(Icons.Filled.Add, "Localized Description") },
                    text = { Text(text = "Extended FAB") },
                )
            },
            // isFloatingActionButtonDocked = false,
            floatingActionButtonPosition = FabPosition.End,
        ) {it->
            LazyColumn(state = listState, modifier = Modifier.fillMaxSize().padding(it)) {
                for (index in 0 until 100) {
                    item {
                        Text(
                            text = "List - $index",
                            modifier = Modifier.padding(24.dp)
                        )
                    }
                }
            }
        }
    }


}
