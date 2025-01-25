package com.example.composedemo

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

import androidx.activity.result.contract.ActivityResultContracts

import android.widget.Toast
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.composedemo.worker.NotificationWorker
import java.util.concurrent.TimeUnit

class HomePage : ComponentActivity() {

    private val requestpermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                scheduleNotification(this)
            } else {
                Toast.makeText(this, "Need notification permission", Toast.LENGTH_SHORT).show()
            }

        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    //check notification  permission android 13+

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        requestpermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    } else {
                        scheduleNotification(this)
                    }
                }
            }
        }
    }
}

private fun scheduleNotification(context: Context) {
    val periodicNotificationRequest: PeriodicWorkRequest =
        PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES).build()

    WorkManager.getInstance(context).enqueue(periodicNotificationRequest)

}