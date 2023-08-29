//Text in Compose | Jetpack Compose

package com.demo.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TextFunction()

        }
    }

    @Preview(
        showBackground = true, device = "id:pixel_6_pro", showSystemUi = true, name = "First View"
    )
    @Composable
    private fun TextFunction() {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .background(Color.LightGray)
        ) {
            Text(
                text = buildAnnotatedString {

                    withStyle(style = SpanStyle(
                        fontSize = 30.sp, color = Color.Red
                    )){
                        append("Pixel")
                    }
                    append("Developer")

                    withStyle(style = SpanStyle(
                        fontSize = 30.sp, color = Color.Magenta
                    )){
                        append("Videos")
                    }
                },
                modifier = Modifier.fillMaxSize(),
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 12.sp,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline
                ),
                maxLines = 2,
            )

            /*
            Text(
                text = "Pixel Developer", fontSize = 22.sp, style = TextStyle(
                    shadow = Shadow(
                        color = Color.Green, offset = Offset(x = 5f, y = 10f), blurRadius = 2f
                    )
                )
            )
            */


        }

    }


}


