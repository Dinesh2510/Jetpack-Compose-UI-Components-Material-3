package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.icons.Icons
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
        val mContext = LocalContext.current
        var selectedItem by remember {
            mutableStateOf(0)
        }
        val manuIcons = listOf(
            R.drawable.baseline_home_24,
            R.drawable.baseline_favorite_24,
            R.drawable.baseline_trending_up_24,
            R.drawable.baseline_account_circle_24
        )

        val menuTitle = listOf(
            "Home",
            "Favorite",
            "Explore",
            "Profile"
        )

        Scaffold(
            topBar = {
                BasicTopAppBar(title = "Listing Demo")
            },
            content = { innerpadding ->
                Column(modifier = Modifier.padding(innerpadding)) {
                    LazyColumn {
                        val listingData = IncludeContent.getListHomeData(mContext)

                        items(listingData.size) {
                            val items = listingData[it]
                            //ui
                            Row(
                                Modifier
                                    .padding(vertical = 10.dp, horizontal = 8.dp)
                                    .clickable { }, verticalAlignment = Alignment.CenterVertically
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

            },
            bottomBar = {
                NavigationBar {
                    manuIcons.forEachIndexed { index, icon ->
                        NavigationBarItem(selected = selectedItem == index,
                            onClick = { selectedItem = index },
                            icon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(icon),
                                    contentDescription = ""
                                )
                            },

                            label = if (selectedItem == index) {
                                { Text(text = menuTitle[index]) }
                            } else {
                                null
                            }


                        )
                    }

                }
            }


        )


    }


}


