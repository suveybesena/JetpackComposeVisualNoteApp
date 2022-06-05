package com.example.jetpackcomposevisualnoteapp.features.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.features.navigation.Screen
import com.example.jetpackcomposevisualnoteapp.features.screen.home.NotesListUiEvent
import com.example.jetpackcomposevisualnoteapp.features.screen.home.NotesListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListView(
    notes: List<NoteModel>,
    navController: NavController,
    viewModel: NotesListViewModel = hiltViewModel()
) {
    LazyColumn(
        contentPadding = PaddingValues(5.dp)
    )
    {
        itemsIndexed(notes) { index, item ->

            NoteItemRow(
                noteDetail = item,
                onNoteClicked = { note ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "noteModelArg",
                        note
                    )
                    navController.navigate(Screen.NoteDetailScreen.route)
                },
                onEditSwipe = { note ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "noteModelArg",
                        note
                    )
                    navController.navigate(Screen.NoteEditScreen.route)
                },
                onDeleteSwipe = {
                    viewModel.handleEvent(NotesListUiEvent.DeleteNote(item))
                    viewModel.handleEvent(NotesListUiEvent.GetAllNotes)
                }
            )
        }
    }
}