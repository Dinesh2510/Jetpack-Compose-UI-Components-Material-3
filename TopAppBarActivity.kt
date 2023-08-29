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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
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
                //Simple TopBar & CenterAlignedTopAppBar
              //  SimpleTopAppBar()
                //pinnedScroll, enterAlwaysScroll, enterAlwaysScroll, exitUntilCollapsedScroll
                ScrollableTopAppBar()
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "First View"
    )
    @Composable
    private fun SimpleTopAppBar() {
        Scaffold(
            topBar = {

                CenterAlignedTopAppBar(
                    title = { Text(text = "Pixel Developer") },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                        }
                    }

                )
            },
            content = { innerpadding ->
                LazyColumn(
                    contentPadding = innerpadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val listing = (0..50).map { it.toString() }
                    items(count = listing.size) {
                        Text(
                            text = listing[it],
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)

                        )
                    }
                }

            }


        )

    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "Second View"
    )
    @Composable
    private fun ScrollableTopAppBar() {

      //  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior() //pinned scroll
     //   val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior() //enteralways scroll
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior() //exituntilcollasped scroll

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {

                //here we can use LargeTopBar MediumTOpBar CenterAlignTOpbar ,or topbar
                LargeTopAppBar(
                    title = { Text(text = "Pixel Developer") },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                        }
                    },
                    scrollBehavior = scrollBehavior

                )
            },
            content = { innerpadding ->
                LazyColumn(
                    contentPadding = innerpadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val listing = (0..50).map { it.toString() }
                    items(count = listing.size) {
                        Text(
                            text = "items: "+listing[it],
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)

                        )
                    }
                }

            }


        )


    }


}
