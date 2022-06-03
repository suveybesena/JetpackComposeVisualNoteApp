package com.example.jetpackcomposevisualnoteapp.features.screen.addnote


import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevisualnoteapp.common.Constants.ADD_NOTE_SCREEN_TITLE
import com.example.jetpackcomposevisualnoteapp.common.Constants.NOTE_DESC_HINT
import com.example.jetpackcomposevisualnoteapp.common.Constants.NOTE_IMAGE_HINT
import com.example.jetpackcomposevisualnoteapp.common.Constants.NOTE_TITLE_HINT
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.features.components.NoteAddBar
import com.example.jetpackcomposevisualnoteapp.features.components.NoteTopTitle
import com.example.jetpackcomposevisualnoteapp.features.navigation.Screen
import java.util.*

@Composable
fun NoteAddScreen(
    viewModel: AddNoteViewModel = hiltViewModel(),
    navController: NavController
) {
    var noteTitle by remember {
        mutableStateOf("")
    }
    var noteDesc by remember {
        mutableStateOf("")
    }
    var noteUrlImage by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    var hourState by remember { mutableStateOf(calendar[Calendar.HOUR_OF_DAY]) }
    var minuteState by remember { mutableStateOf(calendar[Calendar.MINUTE]) }
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            hourState = hour
            minuteState = minute
        }, hourState, minuteState, true
    )
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column() {
            NoteTopTitle(ADD_NOTE_SCREEN_TITLE)
            NoteAddBar(
                hint = NOTE_TITLE_HINT,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                noteTitle = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            NoteAddBar(
                hint = NOTE_DESC_HINT,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                noteDesc = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            NoteAddBar(
                hint = NOTE_IMAGE_HINT,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                noteUrlImage = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            val date = System.currentTimeMillis()
            val noteModel = NoteModel(
                noteUrlImage,
                date,
                noteTitle,
                noteDesc,
                hourState,
                minuteState
            )
            FloatingButton(
                viewModel,
                navController,
                noteModel
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                    onClick = { timePickerDialog.show() }
                ) {
                    Icon(
                        Icons.Filled.Timer,
                        "contentDescription",
                        tint = com.example.jetpackcomposevisualnoteapp.ui.theme.Color.Blue,
                        modifier = Modifier
                            .height(80.dp)
                            .width(80.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun FloatingButton(
    viewModel: AddNoteViewModel = hiltViewModel(),
    navController: NavController,
    noteDetail: NoteModel
) {
    ExtendedFloatingActionButton(
        text =
        { Text(text = ADD_NOTE_SCREEN_TITLE, color = Color.White) },
        onClick = {
            viewModel.handleEvent(AddNoteUiEvent.AddNote(noteDetail))
            navController.navigate(route = Screen.NoteListScreen.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    )
}







