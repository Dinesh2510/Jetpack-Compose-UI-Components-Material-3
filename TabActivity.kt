package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
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
        val act = LocalContext.current as Activity
        var state by remember { mutableStateOf(0) }
        val titlesAndIcons = listOf(
            "Home" to ImageVector.vectorResource(id = R.drawable.baseline_home_24),
            "Favorite" to ImageVector.vectorResource(id = R.drawable.baseline_favorite_24),
            "Profile" to ImageVector.vectorResource(id = R.drawable.baseline_account_circle_24)
        )


        Scaffold(
            topBar = {
                Column {
                    TopAppBar(
                        title = {
                            Text("Tabs Demo", style = MaterialTheme.typography.titleMedium)
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                act.finish()
                            }) {
                                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* doSomething */ }) {
                                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "")
                            }
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                        ),
                    )
                    TabRow(
                        selectedTabIndex = state,
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                    ) {
                        titlesAndIcons.forEachIndexed { index, (title, icon) ->
                            Tab(
                                selected = state == index,
                                onClick = { state = index },
                                text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) },
                                icon = { Icon(icon, contentDescription = null) }
                            )
                        }
                    }
                }
            },
            content = { innPadding ->
            //here ur content 
            // also if you wants to add below listing plz check this link
            // https://github.com/Dinesh2510/Jetpack-Compose-UI-Components-Material-3/tree/main/Basic%20Lists
                IncludeContent.ContentGridCard(innPadding)
            }
        )


    }


}


