package com.example.jetpackcomposevisualnoteapp.features.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
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
import com.example.jetpackcomposevisualnoteapp.features.components.NoteListView
import com.example.jetpackcomposevisualnoteapp.features.navigation.Screen

@Composable
fun NoteListScreen(viewModel: NotesListViewModel = hiltViewModel(), navController: NavController) {

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                "Notes", modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                textAlign = TextAlign.Start,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            NoteList(navController, viewModel)
        }
        ExtendedFloatingActionButton(
            text =
            { Text(text = "Add Note") },
            icon = { Icon(Icons.Filled.Add, "Add Note") },
            onClick = {
                navController.navigate(route = Screen.NoteAddScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )
    }
}

@Composable
fun NoteList(navController: NavController, viewModel: NotesListViewModel) {
    val noteListState = viewModel.uiState.value
    noteListState.notesList?.let { NoteListView(notes = it, navController = navController) }
}


