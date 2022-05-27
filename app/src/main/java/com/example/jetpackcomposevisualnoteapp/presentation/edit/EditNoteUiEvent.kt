package com.example.jetpackcomposevisualnoteapp.presentation.edit

import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel

sealed class EditNoteUiEvent  {
    data class UpdateNote(val noteDetail: NoteModel) : EditNoteUiEvent()
    data class DeleteNote(val noteDetail: NoteModel) : EditNoteUiEvent()
}