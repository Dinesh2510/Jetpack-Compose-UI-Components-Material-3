package com.app.composestorage.notification

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import com.cloud.firepdf.HomePage
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.net.URL
import java.util.concurrent.Executors

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("FCM", "Message received: $remoteMessage")

        // ✅ Move work to a background thread to prevent crashes
        Executors.newSingleThreadExecutor().execute {
            try {
                Log.d("FCM", "Data Payload: ${remoteMessage.data}")

                // ✅ Parse Data Message
                val title = remoteMessage.data["title"] ?: "No Title"
                val body = remoteMessage.data["body"] ?: "No Body"
                val imageUrl = remoteMessage.data["imageUrl"]

                Log.d("FCM", "Parsed Title: $title")
                Log.d("FCM", "Parsed Body: $body")
                Log.d("FCM", "Parsed Image URL: $imageUrl")

                // ✅ Show notification safely on a background thread
                showNotification(this,title, body, imageUrl!!)
            } catch (e: Exception) {
                Log.e("FCM", "Error processing notification: ${e.message}")
            }
        }
    }

    private fun showNotification(
        context: Context,
        title: String,
        message: String,
        imageUrl: String,
    ) {
        val intent = Intent(this, HomePage::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra("screen", title)
            putExtra("messageId", message)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel (required for Android 8+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "default_channel",
                "Default Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Build the notification
        val notificationBuilder = NotificationCompat.Builder(context, "default_channel")
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)


        // Optional: Add an image to the notification
        if (imageUrl.isNotEmpty()) {
            try {
                val bitmap = BitmapFactory.decodeStream(URL(imageUrl).openStream())
                notificationBuilder.setStyle(
                    NotificationCompat.BigPictureStyle().bigPicture(bitmap)
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // Show the notification
        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
    }
}


@Composable
fun NotificationPermissionScreen() {
    val context = LocalContext.current
    var permissionGranted by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionGranted = isGranted
    }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (permissionGranted) {
            Text("✅ Notification permission granted")
        } else {
            Text("❗ Notification permission is required")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }) {
                Text("Request Permission")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                // Open app settings
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                context.startActivity(intent)
            }) {
                Text("Open App Settings")
            }
        }
    }
}

