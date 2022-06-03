package com.example.jetpackcomposevisualnoteapp.features.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevisualnoteapp.common.Constants.ADD_NOTE_SCREEN_TITLE
import com.example.jetpackcomposevisualnoteapp.common.Constants.NOTES_LIST_SCREEN_TITLE
import com.example.jetpackcomposevisualnoteapp.features.components.NoteListShimmer
import com.example.jetpackcomposevisualnoteapp.features.components.NoteListView
import com.example.jetpackcomposevisualnoteapp.features.navigation.Screen

@Composable
fun NoteListScreen(viewModel: NotesListViewModel = hiltViewModel(), navController: NavController) {
    val noteListState = viewModel.uiState.value
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = com.example.jetpackcomposevisualnoteapp.ui.theme.Color.Gray),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .shadow(5.dp)
                    .height(68.dp)
                    .clip(shape = RoundedCornerShape(15))
                    .background(com.example.jetpackcomposevisualnoteapp.ui.theme.Color.Blue)

            ) {
                Text(
                    NOTES_LIST_SCREEN_TITLE,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    textAlign = TextAlign.Start,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,

                    )
            }

            if (noteListState.isLoading == true) {
                repeat(7) {
                    NoteListShimmer()
                }
            } else {
                NoteList(navController, noteListState)
            }

        }
        Row() {
            ExtendedFloatingActionButton(
                text =
                { Text(text = ADD_NOTE_SCREEN_TITLE, color = Color.White) },
                onClick = {
                    navController.navigate(route = Screen.NoteAddScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            )
        }
    }
}

@Composable
fun NoteList(
    navController: NavController,
    noteListState: NotesListUiState
) {
    noteListState.notesList?.let {
        NoteListView(
            notes = it,
            navController = navController
        )
    }
}


