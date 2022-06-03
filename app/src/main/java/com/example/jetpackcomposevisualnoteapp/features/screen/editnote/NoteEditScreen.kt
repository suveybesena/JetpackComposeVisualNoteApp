package com.example.jetpackcomposevisualnoteapp.features.screen.editnote

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevisualnoteapp.common.Constants
import com.example.jetpackcomposevisualnoteapp.common.Constants.DELETE_NOTE
import com.example.jetpackcomposevisualnoteapp.common.Constants.EDIT_SCREEN_TITLE
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.features.components.NoteEditBar
import com.example.jetpackcomposevisualnoteapp.features.components.NoteTopTitle
import com.example.jetpackcomposevisualnoteapp.features.navigation.Screen
import java.util.*

@Composable
fun NoteEditScreen(
    navController: NavController,
    navArg: NoteModel,
    viewModel: NoteEditViewModel = hiltViewModel()

) {
    var noteTitle by remember {
        mutableStateOf(navArg.noteTitle)
    }
    var noteDesc by remember {
        mutableStateOf(navArg.noteDesc)
    }
    var noteImageUrl by remember {
        mutableStateOf(navArg.imageUrl)
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
    val date = System.currentTimeMillis()
    val editedTag = Constants.EDITED_TAG_TITLE
    val noteModel = NoteModel(
        noteImageUrl,
        date,
        noteTitle,
        noteDesc,
        hourState,
        minuteState,
        editedTag,
        navArg.id
    )

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            NoteTopTitle(EDIT_SCREEN_TITLE)
            Spacer(modifier = Modifier.height(10.dp))
            NoteEditBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), initialText = navArg.noteTitle!!
            ) {
                noteTitle = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            NoteEditBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), initialText = navArg.noteDesc!!
            ) {
                noteDesc = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            NoteEditBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), initialText = navArg.imageUrl!!
            ) {
                noteImageUrl = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            ExtendedFloatingActionButton(
                text =
                { Text(text = EDIT_SCREEN_TITLE, color = Color.White) },
                onClick = {
                    viewModel.handleEvent(EditNoteUiEvent.UpdateNote(noteModel))
                    navController.navigate(Screen.NoteListScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            ExtendedFloatingActionButton(
                text =
                { Text(text = DELETE_NOTE, color = Color.White) },
                onClick = {
                    viewModel.handleEvent(EditNoteUiEvent.DeleteNote(noteModel))
                    navController.navigate(route = Screen.NoteListScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                IconButton(onClick = { timePickerDialog.show() }) {
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


