package com.demo.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            BoxDemo()
            ButtonDemo()

        }
    }


    @Preview(
        showBackground = true, device = "id:pixel_6_pro", showSystemUi = true, name = "Second View"
    )
    @Composable
    private fun ButtonDemo() {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(onClick = { /*Onclick here*/ }) {
                Text(text = "Simple Button")
            }

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {

                Text(text = "Button with color")

            }

            Button(onClick = { /*TODO*/ }) {

                Text(text = "Multiple", color = Color.Blue)
                Text(text = "Color", color = Color.Cyan)
            }

            Button(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.outline_home_24),
                    contentDescription = null
                )
                Text(text = "Btn with Icon")
            }


            Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(12.dp)) {

                Text(text = "btn with Rounded Corner")

            }

            Button(
                onClick = { /*TODO*/ },
                border = BorderStroke(1.dp, color = Color.Blue),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
            ) {

                Text(text = "btn with Border", color = Color.Gray)
            }
        }


    }

    @Preview(
        showBackground = true, device = "id:pixel_6_pro", showSystemUi = true, name = "First View"
    )
    @Composable
    private fun BoxDemo() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Button(onClick = { /*TODO*/ }) {
                Text(text = "BUtton clikc me ")

            }


        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Box(
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .background(Color.Blue)
            )


            Box(
                modifier = Modifier
                    .size(200.dp, 200.dp)
                    .background(Color.Cyan)
                    .align(Alignment.Center)
            )

            Box(
                modifier = Modifier
                    .size(150.dp, 120.dp)
                    .background(Color.Green)
                    .align(Alignment.BottomEnd)
            )

        }

    }


}


