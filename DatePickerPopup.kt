package com.app.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.app.myapplication.data.Tools

import com.app.myapplication.ui.theme.AppTheme
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
class DatePickerPopup : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentDatePickerPopup()
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Preview(showBackground = true)
@Composable
fun ContentDatePickerPopup() {
    // Decoupled snackbar host state from scaffold state for demo purposes.
    var dateResult by remember { mutableStateOf("Date Picker") }
    val openDialog = remember { mutableStateOf(true) }

    AppTheme {
        Scaffold(
            topBar = {
                BasicTopAppBar("DatePicker Dialog")
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 100.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            openDialog.value = true
                        }
                    ) {
                        Text(dateResult)
                    }
                }
                if (openDialog.value) {
                    val datePickerState = rememberDatePickerState()
                    val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
                    val confirmEnabled = derivedStateOf { datePickerState.displayMode != null }
                    DatePickerDialog(
                        onDismissRequest = {
                            openDialog.value = false
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    openDialog.value = false
                                    var date = "no selection"
                                    if(datePickerState.selectedDateMillis != null){
                                        date = Tools.convertLongToTime(datePickerState.selectedDateMillis!!)
                                    }
                                    dateResult = date
                                },
                                enabled = confirmEnabled.value
                            ) {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    openDialog.value = false
                                }
                            ) {
                                Text("Cancel")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState)
                    }
                }
            }
        )
    }
}