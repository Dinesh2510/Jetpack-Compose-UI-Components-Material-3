package com.example.composedemo.photopicker

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class PhotoPickerViewModel : ViewModel() {
    // Holds selected images
    val selectedUris = mutableStateListOf<Uri>()

    fun updateSelectedUris(newUris: List<Uri>) {
        Log.d("URI", "PhotoPickerViewModel URI: ${newUris.toString()}")

        selectedUris.clear()
        selectedUris.addAll(newUris)
    }
    fun clearPhotoPicker() {
        selectedUris.clear()
    }
}


// Add below libs in Build.Gradle
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("io.coil-kt:coil-video:2.4.0")