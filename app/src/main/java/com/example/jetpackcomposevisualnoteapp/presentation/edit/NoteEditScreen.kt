package com.example.jetpackcomposevisualnoteapp.presentation.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.presentation.addnote.NoteAddBar

@Composable
fun NoteEditScreen(
    navController: NavController,
    viewModel: NoteEditViewModel = hiltViewModel()
) {

    lateinit var noteTitle: String
    var noteDesc = ""
    var noteImageUrl = ""
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
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
                noteImageUrl = it
            }
            Spacer(modifier = Modifier.height(10.dp))
            ExtendedFloatingActionButton(
                text =
                { Text(text = "Edit Note") },
                icon = { Icon(Icons.Filled.Add, "") },
                onClick = {
                    val date = System.currentTimeMillis()
                    val noteModel = NoteModel(
                        "https://www.ikea.com/ca/en/images/products/fejka-artificial-potted-plant-indoor-outdoor-monstera__0614197_pe686822_s5.jpg?f=s",
                        date,
                        "noteTitle",
                        "noteDesc"
                    )
                    viewModel.handleEvent(EditNoteUiEvent.UpdateNote(noteModel))
                    navController.navigate("note_list_screen")
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
                    // val date = System.currentTimeMillis()
                    // val noteModel = NoteModel(noteImageUrl, date, noteTitle, noteDesc)
                    // viewModel.handleEvent(EditNoteUiEvent.DeleteNote(noteModel))
                    navController.navigate("note_list_screen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
        }
    }
}

