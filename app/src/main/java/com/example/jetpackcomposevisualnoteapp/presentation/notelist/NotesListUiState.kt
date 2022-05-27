package com.example.jetpackcomposevisualnoteapp.presentation.notelist

import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel

data class NotesListUiState(
    val error: String? = null,
    val isLoading: Boolean? = null,
    val notesList: List<NoteModel>? = null
)
