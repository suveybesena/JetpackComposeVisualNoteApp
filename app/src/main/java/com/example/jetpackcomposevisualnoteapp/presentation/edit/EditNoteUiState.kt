package com.example.jetpackcomposevisualnoteapp.presentation.edit



data class EditNoteUiState(
    var error: String? = null,
    var isLoading: Boolean? = null,
    var success: Boolean? = null
)
