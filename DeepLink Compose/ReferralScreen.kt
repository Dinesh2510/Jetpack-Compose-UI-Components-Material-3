package com.app.composestorage.deeplink

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ReferralScreen(code: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Referral Code:${code ?: "No Code for referral"}",
            style = MaterialTheme.typography.headlineMedium
        )
    }


}

