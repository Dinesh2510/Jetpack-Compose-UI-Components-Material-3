package com.demo.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBoxScope
import androidx.compose.material3.TooltipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.jetpackcomposedemo.ui.theme.AppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                //SimpleSwitch()
                SwitchWithThumbIconSample()
            }
        }
    }

    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "First View"
    )
    @Composable
    private fun SimpleSwitch() {
        var checked by remember {
            mutableStateOf(true)
        }

        Switch(
            checked = checked, onCheckedChange = { checked = it }, modifier = Modifier.semantics {
                contentDescription = "Demo"
            }

        )

    }


    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "Second View"
    )
    @Preview
    @Composable
    fun SwitchWithThumbIconSample() {

        var checked by remember {
            mutableStateOf(true)
        }

        val icon: (@Composable () -> Unit)? = if (checked) {
            {

                Image(
                    painterResource(id = R.drawable.baseline_nightlight_24),
                    contentDescription = null
                )
            }
        } else {
            {
                Image(
                    painterResource(id = R.drawable.baseline_light_mode_24),
                    contentDescription = null
                )
            }
        }

        Scaffold { it ->
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Switch(
                    checked = checked, onCheckedChange = { checked = it },
                    thumbContent = icon
                )
                Text(
                    text = "Switch is " + if (checked) "Night Mode " else "Light Mode",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

            }


        }


    }


}
