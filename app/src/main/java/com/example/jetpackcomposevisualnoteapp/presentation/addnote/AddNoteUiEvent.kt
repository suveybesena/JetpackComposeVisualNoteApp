package com.example.jetpackcomposevisualnoteapp.presentation.addnote

import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel

sealed class AddNoteUiEvent {
    data class AddNote(val noteDetail: NoteModel) : AddNoteUiEvent()
}