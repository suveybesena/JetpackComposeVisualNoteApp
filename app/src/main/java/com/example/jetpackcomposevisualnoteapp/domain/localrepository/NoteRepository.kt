package com.example.jetpackcomposevisualnoteapp.domain.localrepository

import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun addNote(noteDetail: NoteModel)
    suspend fun deleteNote(noteDetail: NoteModel)
    suspend fun updateNote(noteDetail: NoteModel)
    fun getAllNotes(): Flow<List<NoteModel>>
}