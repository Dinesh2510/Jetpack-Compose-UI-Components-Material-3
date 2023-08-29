package com.demo.jetpackcomposedemo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material.icons.Icons

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
            Column {
                Text(
                    text = "Vertical List", fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                VerticalList()
                Text(
                    text = "Vertical Grid List", fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                VerticalGridList()
                Text(
                    text = "Horizontal List", fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                HorizontalList()
                Text(
                    text = "Horizontal Grid List", fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                HorizontalGridList()
            }


        }
    }

    @Preview(
        showBackground = true,
        backgroundColor = 0xFFF0EAE2,
        device = "id:pixel_7_pro",
        showSystemUi = true,
        name = "First View"
    )
    @Composable
    private fun VerticalList() {

        LazyColumn() {

            items(prepareOptionsList()) { it ->

                ItemLayoutVertical(optionsList = it)

            }
        }

    }

    @Preview(
        showBackground = true,
        backgroundColor = 0xFFF0EAE2,
        device = "id:pixel_7_pro",
        showSystemUi = true,
        name = "Second View"
    )
    @Composable
    private fun VerticalGridList() {

        LazyVerticalGrid(columns = GridCells.Adaptive(128.dp)) {
            items(prepareOptionsList()) {
                ItemLayout(optionsList = it)

            }

        }

    }


    @Preview(
        showBackground = true,
        backgroundColor = 0xFFF0EAE2,
        device = "id:pixel_7_pro",
        showSystemUi = true,
        name = "Third View"
    )
    @Composable
    private fun HorizontalList() {

        LazyRow() {

            items(prepareOptionsList()) {

                ItemLayout(optionsList = it)
            }
        }


    }

    @Preview(
        showBackground = true,
        backgroundColor = 0xFFF0EAE2,
        device = "id:pixel_7_pro",
        showSystemUi = true,
        name = "Fourth View"
    )
    @Composable
    private fun HorizontalGridList() {

        LazyHorizontalGrid(rows = GridCells.Fixed(4)) {
            items(prepareOptionsList()) {

                ItemLayout(optionsList = it)
            }
        }


    }


    @Composable
    fun ItemLayoutVertical(optionsList: ImagesList) {
        Card(
            shape = RoundedCornerShape(size = 4.dp),
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width(192.dp)
            ) {
                Image(
                    painter = painterResource(optionsList.icon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(56.dp)
                )
                Text(
                    text = (optionsList.text),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }

    @Composable
    private fun ItemLayout(
        optionsList: ImagesList,
        context: Context = LocalContext.current.applicationContext,
    ) {
        Card(
            shape = RoundedCornerShape(size = 12.dp),
            modifier = Modifier.padding(4.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {}
                    .padding(all = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = optionsList.icon),
                    modifier = Modifier.size(size = 100.dp),
                    contentDescription = null
                )
            }
        }
    }

    private fun prepareOptionsList(): MutableList<ImagesList> {
        val imagesListMutableList = mutableListOf<ImagesList>()
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_1, text = "K"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_2, text = "L"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_3, text = "M"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_4, text = "N"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_5, text = "O"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_6, text = "P"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_7, text = "Q"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_8, text = "R"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_9, text = "S"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_10, text = "A"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_11, text = "B"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_12, text = "C"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_13, text = "D"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_14, text = "E"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_15, text = "F"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_16, text = "G"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_17, text = "H"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_18, text = "L"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_19, text = "I"))
        imagesListMutableList.add(ImagesList(icon = R.drawable.image_20, text = "J"))
        return imagesListMutableList
    }

    data class ImagesList(val icon: Int, val text: String)

}
