package com.demo.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.PlainTooltipState
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.RichTooltipState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBoxScope
import androidx.compose.material3.TooltipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.jetpackcomposedemo.ui.theme.AppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppTheme {
                Scaffold {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //PlainToolTipDemo()
                        // PlainToolTipCustom()

                        //RichToolTipDemo()
                        RichToolTipCustom()

                    }
                }

            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "Fourth View"
    )
    @Composable
    private fun RichToolTipCustom() {

        val tooltipState = RichTooltipState()
        val scope = rememberCoroutineScope()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RichTooltipBox(
                text = {
                    Text(text = richTooltipText)
                },
                action = {
                    TextButton(onClick = { scope.launch { tooltipState.dismiss() } }) {
                        Text(text = richTooltipActionText)
                    }
                },
                title = { Text(text = richTooltipSubheadText) },
                tooltipState = tooltipState


            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.tooltipAnchor()
                ) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = "demo")

                }

            }
            Spacer(modifier = Modifier.requiredHeight(30.dp))
            OutlinedButton(onClick = {scope.launch{tooltipState.show()}}) {
                Text(text = "Display Rich Tooltip")
                
            }

        }


    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "THried View"
    )
    @Composable
    private fun RichToolTipDemo() {

        val tooltipState = RichTooltipState()
        val scope = rememberCoroutineScope()

        RichTooltipBox(
            text = {
                Text(text = richTooltipText)
            },
            action = {
                TextButton(onClick = { scope.launch { tooltipState.dismiss() } }) {
                    Text(text = richTooltipActionText)
                }
            },
            title = { Text(text = richTooltipSubheadText) },
            tooltipState = tooltipState


        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.tooltipAnchor()
            ) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "demo")

            }

        }


    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "Second View"
    )
    @Composable
    private fun PlainToolTipCustom() {
        val tooltipState = PlainTooltipState()
        val Scope = rememberCoroutineScope()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PlainTooltipBox(
                tooltip = { Text("Add to list") },
                tooltipState = tooltipState
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Localized Description"
                )
            }
            Spacer(Modifier.requiredHeight(30.dp))
            OutlinedButton(
                onClick = { Scope.launch { tooltipState.show() } }
            ) {
                Text("Display tooltip")
            }
        }


    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "First View"
    )
    @Composable
    private fun PlainToolTipDemo() {

        PlainTooltipBox(tooltip = {
            Text(text = "Add to Favorites")
        }) {
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.tooltipAnchor()) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            }
        }

    }


}

const val richTooltipSubheadText = "Permissions"
const val richTooltipText =
    "Configure permissions for selected service accounts. " + "You can add and remove service account members and assign roles to them. " + "Visit go/permissions for details"
const val richTooltipActionText = "Request Access"