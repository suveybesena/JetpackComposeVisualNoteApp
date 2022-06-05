package com.example.jetpackcomposevisualnoteapp.data.repository

import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.domain.localrepository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class FakeNoteRepositoryImpl : NoteRepository {

    private val notes = mutableListOf<NoteModel>()
    private val notesFlow = MutableStateFlow<List<NoteModel>>(notes)

    override suspend fun addNote(noteDetail: NoteModel) {
        notes.add(noteDetail)
        refreshData()
    }

    override suspend fun deleteNote(noteDetail: NoteModel) {
        notes.remove(noteDetail)
        refreshData()
    }

    override suspend fun updateNote(noteDetail: NoteModel) {
    }

    override fun getAllNotes(): Flow<List<NoteModel>> {
        return flow { emit(notes) }
    }

    private fun refreshData() {
        notesFlow.value = notes
    }
}