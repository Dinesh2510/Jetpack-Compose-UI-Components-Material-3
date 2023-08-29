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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                ScaffoldDemo()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "First View"
    )
    @Composable
    private fun ScaffoldDemo() {
        val mContext = LocalContext.current
        val snackbarHostState = remember {
            SnackbarHostState()
        }
        val scope = rememberCoroutineScope()

        Scaffold(
            topBar = {

                TopAppBar(
                    title = { Text(text = "Pixel Developer") },
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        scrolledContainerColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Info, contentDescription = null)

                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Favorite, contentDescription = null)

                        }
                    },
                    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                )

            },
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            },
            content = { innerpadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerpadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = stringResource(id = R.string.long_txt),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(20.dp)
                    )

                    Button(
                        onClick = {
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Hurry! Our msg ",
                                    actionLabel = "Action",
                                    withDismissAction = true,
                                    duration = SnackbarDuration.Indefinite
                                )
                                when (result) {
                                    SnackbarResult.ActionPerformed -> {

                                    }

                                    SnackbarResult.Dismissed -> {

                                    }
                                }
                            }
                        }, modifier = Modifier.wrapContentSize()
                    ) {

                        Text(text = "Show SnackBar")
                    }

                }

            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    Toast.makeText(mContext, "Floating Btn click", Toast.LENGTH_SHORT).show()
                }, shape = FloatingActionButtonDefaults.shape,
                    content = {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            bottomBar = {

                BottomAppBar (
                    content = {
                        Row(modifier = Modifier.fillMaxWidth(1f),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                            ) {

                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Default.Home, contentDescription = null)
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Default.Star, contentDescription = null)
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                            }

                        }
                    }

                )
            }
        )

    }


}
