package com.demo.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
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
import androidx.compose.material3.SuggestionChip
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

AssistChipSample()
                    }
                }

            }
        }

    }

    @Preview
    
    @Composable
    fun AssistChipSample() {
        AssistChip(
            onClick = { /* Do something! */ },
            label = { Text("Assist Chip") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Localized description",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )
    }

    @Preview
    
    @Composable
    fun ElevatedAssistChipSample() {
        ElevatedAssistChip(
            onClick = { /* Do something! */ },
            label = { Text("Assist Chip") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Localized description",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    
    @Composable
    fun FilterChipSample() {
        var selected by remember { mutableStateOf(false) }
        FilterChip(
            selected = selected,
            onClick = { selected = !selected },
            label = { Text("Filter chip") },
            leadingIcon = if (selected) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Localized Description",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    
    @Composable
    fun ElevatedFilterChipSample() {
        var selected by remember { mutableStateOf(false) }
        ElevatedFilterChip(
            selected = selected,
            onClick = { selected = !selected },
            label = { Text("Filter chip") },
            leadingIcon = if (selected) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Localized Description",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    
    @Composable
    fun FilterChipWithLeadingIconSample() {
        var selected by remember { mutableStateOf(false) }
        FilterChip(
            selected = selected,
            onClick = { selected = !selected },
            label = { Text("Filter chip") },
            leadingIcon = if (selected) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Localized Description",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    
    @Composable
    fun InputChipSample() {
        var selected by remember { mutableStateOf(false) }
        InputChip(
            selected = selected,
            onClick = { selected = !selected },
            label = { Text("Input Chip") },
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    
    @Composable
    fun InputChipWithAvatarSample() {
        var selected by remember { mutableStateOf(false) }
        InputChip(
            selected = selected,
            onClick = { selected = !selected },
            label = { Text("Input Chip") },
            avatar = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Localized description",
                    Modifier.size(InputChipDefaults.AvatarSize)
                )
            }
        )
    }

    @Preview
    
    @Composable
    fun SuggestionChipSample() {
        SuggestionChip(
            onClick = { /* Do something! */ },
            label = { Text("Suggestion Chip") }
        )
    }

    @Preview
    
    @Composable
    fun ElevatedSuggestionChipSample() {
        ElevatedSuggestionChip(
            onClick = { /* Do something! */ },
            label = { Text("Suggestion Chip") }
        )
    }

    @Preview
    
    @Composable
    fun ChipGroupSingleLineSample() {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                repeat(9) { index ->
                    AssistChip(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        onClick = { /* do something*/ },
                        label = { Text("Chip $index") }
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalLayoutApi::class)
    @Preview
    
    @Composable
    fun ChipGroupReflowSample() {
        Column {
            FlowRow(
                Modifier
                    .fillMaxWidth(1f)
                    .wrapContentHeight(align = Alignment.Top),
                horizontalArrangement = Arrangement.Start,
            ) {
                repeat(10) { index ->
                    AssistChip(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .align(alignment = Alignment.CenterVertically),
                        onClick = { /* do something*/ },
                        label = { Text("Chip $index") }
                    )
                }
            }
        }
    }}