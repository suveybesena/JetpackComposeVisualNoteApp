package com.example.jetpackcomposevisualnoteapp.features.screen.home

import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel

data class NotesListUiState(
    val error: String? = null,
    val isLoading: Boolean? = null,
    var notesList: List<NoteModel>? = null
)
