package com.app.composestorage.deeplink

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProductDetailScreen(productId: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Product ID:${productId ?: "Product Id not found"} ",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
