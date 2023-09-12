package com.demo.jetpackcomposedemo

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
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DashDividerDemo()
                }
            }
        }
    }

    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "Second View"
    )
    @Composable
    private fun DashDividerDemo() {
        LazyColumn(){
            items(25){
                Text(text = "Items $it", modifier = Modifier.padding(start = 10.dp))
                DashDivider(
                    color = Color.Red,
                    thickness = 2.dp,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
            
            
        }


    }

    @Composable
    fun DashDivider(
        thickness: Dp,
        color: Color,
        phase: Float = 10f,
        intervals: FloatArray = floatArrayOf(10f, 10f),
        modifier: Modifier
    ) {
        Canvas(modifier = modifier) {
            val dividerHeight = thickness.toPx()
            drawRoundRect(
                color = color,
                style = Stroke(
                    width = dividerHeight,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals,
                        phase
                    )
                )
            )
        }


    }


}


