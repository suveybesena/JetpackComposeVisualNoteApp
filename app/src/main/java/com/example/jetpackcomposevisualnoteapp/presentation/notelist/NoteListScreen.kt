package com.example.jetpackcomposevisualnoteapp.presentation.notelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NoteListScreen(viewModel: NotesListViewModel = hiltViewModel(), navController: NavController) {

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(
                "Notes", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            NoteList(navController = navController, viewModel = viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            ExtendedFloatingActionButton(
                text =
                { Text(text = "Add Note") },
                icon = { Icon(Icons.Filled.Add, "") },
                onClick = {
                    navController.navigate("note_add_screen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
        }
    }
}

@Composable
fun NoteList(navController: NavController, viewModel: NotesListViewModel) {
    //val notesState = viewModel.uiState.value
    val noteListState = remember { viewModel.uiState.value }
    noteListState.notesList?.let { NoteListView(notes = it, navController = navController) }
    //notesState.notesList?.let { NoteListView(it, navController = navController) }
    //println(notesState.notesList)
    //val noteImage =
    //    "data:image/jpeg;base64,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"
    //val imagw2 =
    //    "https://www.cumhuriyet.com.tr/Archive//2022/4/4/110317613-aarn-giri-3tyzjgsbwbk-unsplash.jpg"
    //val noteDesc = "aaa"
    //val noteTitle = "bbbb"
    //val date = System.currentTimeMillis()
    //val noteModel = NoteModel(noteImage, date, noteTitle, noteDesc)
    //val noteModel2 = NoteModel(imagw2, date, noteTitle, noteDesc)
    //val list = arrayListOf<NoteModel>()
    //list.add(noteModel)
    //list.add(noteModel2)
    //NoteListView(list, navController = navController)
}

@Composable
fun NoteListView(notes: List<NoteModel>, navController: NavController) {
    LazyColumn(contentPadding = PaddingValues(5.dp)) {
        items(notes) { notes ->
            NoteItemRow(navController = navController, noteDetail = notes)
        }
    }
}

@Composable
fun NoteItemRow(navController: NavController, noteDetail: NoteModel) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navController.navigate("note_edit_screen")
        }) {
        GlideImage(
            imageModel = noteDetail.imageUrl,
            modifier = Modifier
                .height(130.dp)
                .width(130.dp)
                .padding(5.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            noteDetail.noteTitle?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            noteDetail.noteDesc?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

