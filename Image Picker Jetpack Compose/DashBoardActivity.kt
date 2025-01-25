package com.example.composedemo


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil3.compose.rememberAsyncImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class DashBoardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SingleButtonImagePicker()
                }
            }
        }
    }
}

@Composable
fun SingleButtonImagePicker() {
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var isSending by remember { mutableStateOf(false) }
    selectedImageUri = null
    // Launchers for Gallery and Camera
    val launcherGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    val launcherCamera = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            val uri = saveBitmapToCache(context, bitmap)
            selectedImageUri = uri
        }
    }

    // Permissions based on API level
    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_MEDIA_IMAGES
        )
    } else {
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsResult ->
        if (permissionsResult.values.all { it }) {
            showDialog = true // Show the dialog box after permissions are granted
        } else {
            Toast.makeText(context, "Permissions are required to proceed.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                if (hasPermissions(context, permissions)) {
                    showDialog = true // Directly show dialog if permissions are already granted
                } else {
                    permissionLauncher.launch(permissions) // Request permissions
                }
            }
        ) {
            Text("Pick Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the selected image
        selectedImageUri?.let {
            Log.d("TAG_selectedImageUri", "SingleButtonImagePicker: $selectedImageUri")
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Add button to send the image to the API
            Button(
                onClick = {
                    isSending = true
                    sendImageToApi(context, it) { success ->
                        isSending = false
                        if (success) {
                            selectedImageUri = null // Clear the image once sent successfully
                            Toast.makeText(
                                context,
                                "Image uploaded successfully.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(context, "Failed to upload image.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                },
                enabled = !isSending // Disable the button while sending
            ) {
                Text(if (isSending) "Uploading..." else "Upload")
            }
        }

        // Dialog to choose between Gallery and Camera
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Choose an Option") },
                text = { Text("Please select an option to capture or pick an image.") },
                confirmButton = {
                    Button(onClick = {
                        launcherGallery.launch("image/*")
                        showDialog = false
                    }) {
                        Text("Gallery")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        launcherCamera.launch(null)
                        showDialog = false
                    }) {
                        Text("Camera")
                    }
                }
            )
        }
    }
}

// Function to check if permissions are granted
fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
    return permissions.all { permission ->
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
}

// Helper function to save Bitmap to cache and return URI
fun saveBitmapToCache(context: Context, bitmap: Bitmap): Uri {
    val fileName = "IMG_${System.currentTimeMillis()}.jpg" // Random file name
    val file = File(context.cacheDir, fileName)
    file.outputStream().use { out ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
    }
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )
}

// Function to convert URI to Base64 string
fun uriToBase64(context: Context, uri: Uri): String? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bytes = inputStream?.readBytes()
        Base64.encodeToString(bytes, Base64.NO_WRAP)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

// Function to convert URI to MultipartBody.Part
fun uriToMultipart(context: Context, uri: Uri, paramName: String = "file"): MultipartBody.Part? {
    return try {
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"
        val inputStream = context.contentResolver.openInputStream(uri)
        val bytes = inputStream?.readBytes() ?: return null
        val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), bytes)
        MultipartBody.Part.createFormData(paramName, fileName, requestBody)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun sendImageToApi(context: Context, uri: Uri, callback: (Boolean) -> Unit) {
    // Convert to Base64
    val base64String = uriToBase64(context, uri!!)
    Log.d("TAG_selectedImageUri", "Base64 String: $base64String")

    // Convert to Multipart
    val multipartBody = uriToMultipart(context, uri)
    Log.d("TAG_selectedImageUri", "Multipart Part: $multipartBody")

    if (multipartBody != null) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000L) // Simulating network delay
            withContext(Dispatchers.Main) {
                callback(true)
            }
        }


    } else {
        callback(false)
    }
}