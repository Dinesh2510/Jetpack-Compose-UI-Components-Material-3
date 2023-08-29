package com.demo.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.PlainTooltipState
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.RichTooltipState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBoxScope
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties.ToggleableState
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
                Scaffold {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//                        CheckboxSample()
//                        CheckboxTextSample()
                        TriStateCheckBoxDemo()
                    }
                }

            }
        }

    }

    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "Third View"
    )
    @Composable
    private fun TriStateCheckBoxDemo() {
        val (state, onStateChange )= remember {
            mutableStateOf(true)
        }
        val (state2, onStateChange2 )= remember {
            mutableStateOf(true)
        }
        val parentState = remember(state, state2) {
            if (state && state2) androidx.compose.ui.state.ToggleableState.On
            else if(!state && !state2) androidx.compose.ui.state.ToggleableState.Off
            else androidx.compose.ui.state.ToggleableState.Indeterminate
        }

        val onParentClick = {
            val s= parentState!= androidx.compose.ui.state.ToggleableState.On
            onStateChange(s)
            onStateChange2(s)
        }

        TriStateCheckbox(state = parentState, onClick = onParentClick)
        Spacer(modifier = Modifier.size(16.dp))
        Column(Modifier.padding(start = 10.dp)) {
            Checkbox(checked = state, onCheckedChange = onStateChange)
            Spacer(modifier = Modifier.size(25.dp))
            Checkbox(checked = state2, onCheckedChange = onStateChange2)
        }
    }

    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "Second View"
    )
    @Composable
    private fun CheckboxTextSample() {
        val (checkedState, onStageChange) = remember {
            mutableStateOf(true)
        }
        Row(
            Modifier
                .toggleable(
                    value = checkedState,
                    onValueChange = { onStageChange(!checkedState) },
                    role = Role.Checkbox
                )
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(checked = checkedState, onCheckedChange = null)
            Text(text = "Option A", modifier = Modifier.padding(start = 16.dp))
        }


    }

    @Preview(
        showBackground = true, device = "id:pixel_7_pro", showSystemUi = true, name = "First View"
    )
    @Composable
    fun CheckboxSample() {

        val checkState = remember {
            mutableStateOf(true)
        }
        Checkbox(checked = checkState.value, onCheckedChange = {
            checkState.value = it
        })

    }
}