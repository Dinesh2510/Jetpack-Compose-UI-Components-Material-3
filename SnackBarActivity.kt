package com.demo.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.demo.jetpackcomposedemo.ui.theme.AppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ScaffoldSimpleSnackBar()
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "First View"
    )
    @Composable
    private fun ScaffoldSimpleSnackBar() {
        val snackbarHostState = remember {
            SnackbarHostState()
        }
        val scope = rememberCoroutineScope()

        Scaffold(
            snackbarHost = {
                SnackbarHost(snackbarHostState) { data ->
                    Snackbar {
                        Text(
                            text = data.visuals.message,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(onClick = {
                    scope.launch {
                        val longmsg =
                            "VeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVery Long msg"
                        snackbarHostState.showSnackbar(
                            message = longmsg,
                            actionLabel = "Action",
                            withDismissAction = true,
                            duration = SnackbarDuration.Indefinite
                        )
                    }
                }) {
                    Text(text = "Click Me")
                }

            },
            content = { innerpadding ->
                Text(
                    text = "Pixel Developer",
                    modifier = Modifier
                        .padding(innerpadding)
                        .fillMaxSize()
                        .wrapContentSize(),
                    fontSize = 30.sp
                )
            }


        )

    }
}
