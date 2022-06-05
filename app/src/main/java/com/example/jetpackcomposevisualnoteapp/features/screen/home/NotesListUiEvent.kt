package com.example.jetpackcomposevisualnoteapp.features.screen.home

import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel


sealed class NotesListUiEvent {
    object GetAllNotes : NotesListUiEvent()
    data class DeleteNote(val noteDetail: NoteModel) : NotesListUiEvent()
}

