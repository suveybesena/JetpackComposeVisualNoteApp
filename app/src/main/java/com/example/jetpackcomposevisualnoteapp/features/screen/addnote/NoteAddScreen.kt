package com.example.jetpackcomposevisualnoteapp.features.screen.addnote


import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.features.components.NoteAddBar
import com.example.jetpackcomposevisualnoteapp.features.navigation.Screen

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
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column() {
            Text(
                "Add Notes", modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                textAlign = TextAlign.Start,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            NoteAddBar(
                hint = "Note Title",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                noteTitle = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            NoteAddBar(
                hint = "Note Description",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                noteDesc = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            NoteAddBar(
                hint = "Note Image Url",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                noteUrlImage = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            FloatingButton(viewModel, navController, noteTitle, noteDesc, noteUrlImage)
        }
    }
}

@Composable
fun FloatingButton(
    viewModel: AddNoteViewModel = hiltViewModel(),
    navController: NavController,
    noteTitle: String,
    noteDesc: String,
    noteUrl: String
) {
    ExtendedFloatingActionButton(
        text =
        { Text(text = "Add Note") },
        icon = { Icon(Icons.Filled.Add, "") },
        onClick = {
            val date = System.currentTimeMillis()
            val noteModel = NoteModel(
                noteUrl,
                date,
                noteTitle,
                noteDesc
            )
            viewModel.handleEvent(AddNoteUiEvent.AddNote(noteModel))
            navController.navigate(route = Screen.NoteListScreen.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    )
}


@Preview(showBackground = true)
@Composable
fun FloatingButton() {
    ExtendedFloatingActionButton(
        text =
        { Text(text = "Add") },
        icon = { Icon(Icons.Filled.Add, "ExtendedFloatingActionButton") },
        onClick = {
            Log.v("FloatingActionButton", "Clicked")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    )
}




