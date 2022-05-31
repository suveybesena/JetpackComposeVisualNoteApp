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
    val noteListState = remember { viewModel.uiState.value }
    noteListState.notesList?.let { NoteListView(notes = it, navController = navController) }
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

