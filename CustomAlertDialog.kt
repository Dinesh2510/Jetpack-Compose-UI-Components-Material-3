package com.demo.jetpackcomposedemo

import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardActions.Companion.Default

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardOptions.Companion.Default
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeAction.Companion.Search
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    MyCustomDialog()
                }
            }
        }
    }

    @Preview(name = "Custom Alert")
    @Composable
    private fun MyCustomDialog() {
        var openAlert = remember {
            mutableStateOf(false)
        }


        Button(
            onClick = { openAlert.value = true },
            modifier = Modifier.width(200.dp).height(50.dp)
        ) {
            Text(text = "Click me!")
        }

        if (openAlert.value) {
            CustomDialogUI(openAlert)
        }


    }

    @Composable
    private fun CustomDialogUI(openDialogBox: MutableState<Boolean>) {
        Dialog(onDismissRequest = { openDialogBox.value = false }) {
            CustomUI(openDialogBox)
        }


    }

    @Composable
    private fun CustomUI(openDialog: MutableState<Boolean>) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.background(Color.White)
            ) {

                /**Image*/
                Image(
                    painter = painterResource(id = R.drawable.baseline_circle_notifications_24),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(
                        color = Color.Magenta
                    ),
                    modifier = Modifier
                        .padding(top = 35.dp)
                        .height(70.dp)
                        .fillMaxWidth()
                )

                Column(Modifier.padding(16.dp)) {
                    Text(
                        text = "Get Updates",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium,
                    )

                    Text(
                        text = "Allow permisson to send notifications when new update added on play store!",

                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(10.dp, 25.dp, 25.dp, 25.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyMedium

                    )


                }
                /** Buttons*/
                Row(
                    Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(onClick = { openDialog.value = false }) {
                        Text(
                            text = "Not now", fontWeight = FontWeight.Bold, color = Color.Blue,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }

                    TextButton(onClick = { openDialog.value = false }) {
                        Text(
                            text = "Allow", fontWeight = FontWeight.Bold, color = Color.Black,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }

                }


            }


        }

    }


}