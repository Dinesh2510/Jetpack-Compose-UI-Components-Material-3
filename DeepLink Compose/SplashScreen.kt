package com.app.composestorage.deeplink

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cloud.firepdf.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigate: (String) -> Unit) {
    LaunchedEffect(true) {
        delay(1500)
        onNavigate("referral/DefaultCode") // fallback navigation
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Logo Image
            Image(
                painter = painterResource(id = R.drawable.logo), // replace with your logo name
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 24.dp),
                contentScale = ContentScale.Fit
            )

            // App Name or Splash Text
            Text(
                text = "Splash Screen",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
