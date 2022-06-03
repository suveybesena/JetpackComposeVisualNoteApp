package com.example.jetpackcomposevisualnoteapp.features.screen.editnote


data class EditNoteUiState(
    var error: String? = null,
    var isLoading: Boolean? = null,
    var success: Boolean? = null
)
