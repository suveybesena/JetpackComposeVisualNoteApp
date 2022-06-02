package com.example.jetpackcomposevisualnoteapp.features.screen.editnote

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevisualnoteapp.common.Constants
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.features.components.NoteEditBar
import com.example.jetpackcomposevisualnoteapp.features.navigation.Screen

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
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                "Edit Notes", modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                textAlign = TextAlign.Start,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(10.dp))
            NoteEditBar(
                hint = "Note Title",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), initialText = navArg.noteTitle!!
            ) {
                noteTitle = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            NoteEditBar(
                hint = "Note Description",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), initialText = navArg.noteDesc!!
            ) {
                noteDesc = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            NoteEditBar(
                hint = "Note Image Url",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), initialText = navArg.imageUrl!!
            ) {
                noteImageUrl = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            ExtendedFloatingActionButton(
                text =
                { Text(text = "Edit Note") },
                icon = { Icon(Icons.Filled.Add, "") },
                onClick = {
                    val date = System.currentTimeMillis()
                    val editedTag = Constants.EDITED_TAG_TITLE
                    val noteModel = NoteModel(
                        noteImageUrl,
                        date,
                        noteTitle,
                        noteDesc,
                        editedTag,
                        navArg.id
                    )
                    viewModel.handleEvent(EditNoteUiEvent.UpdateNote(noteModel))
                    navController.navigate(Screen.NoteListScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            ExtendedFloatingActionButton(
                text =
                { Text(text = "Delete Note") },
                icon = { Icon(Icons.Filled.Add, "") },
                onClick = {
                    val date = System.currentTimeMillis()
                    val noteModel = NoteModel(
                        noteImageUrl,
                        date,
                        noteTitle,
                        noteDesc,
                        id = navArg.id
                    )
                    viewModel.handleEvent(EditNoteUiEvent.DeleteNote(noteModel))
                    navController.navigate(route = Screen.NoteListScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
        }
    }
}


