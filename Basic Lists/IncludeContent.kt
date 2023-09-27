package com.demo.jetpackcomposedemo

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
object IncludeContent {

    @Composable
    fun getListingContent() {
        val mContext = LocalContext.current
        LazyColumn {

            items(count = 25) {
                val item = it
                Row(
                    modifier = Modifier
                        .clickable {}
                        .padding(vertical = 12.dp, horizontal = 18.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Card(
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                    ) {
                        Image(
                            painterResource(R.drawable.image_1),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Spacer(modifier = Modifier.width(18.dp))
                    Column {
                        Text(
                            text = "Order ID #:$item",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W500)
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
        }
    }


    fun getListHomeData(context: Context): MutableList<HomeModel> {
        var items: MutableList<HomeModel> = ArrayList()
        val images = context.resources.obtainTypedArray(R.array.list_one_image)
        val titles = context.resources.getStringArray(R.array.list_one_title)
        for (i in titles.indices) {
            items.add(
                HomeModel(id = i, title = titles[i], subtitle = "", image = images.getResourceId(i, -1))
            )
        }
        items = items.toMutableList()
        return items
    }

}
