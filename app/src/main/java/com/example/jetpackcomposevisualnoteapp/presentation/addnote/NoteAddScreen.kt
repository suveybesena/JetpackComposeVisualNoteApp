package com.example.jetpackcomposevisualnoteapp.presentation.addnote


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.presentation.Screen

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
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(10.dp))
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

@Composable
fun NoteAddBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onChanged: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    Box(modifier = modifier) {
        BasicTextField(value = text, onValueChange = {
            text = it
            onChanged(it)
        },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.Gray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}


