package com.example.jetpackcomposevisualnoteapp.data.localrepository

import com.example.jetpackcomposevisualnoteapp.data.local.NoteDAO
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.domain.localrepository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDAO: NoteDAO) :
    NoteRepository {
    override suspend fun addNote(noteDetail: NoteModel) =
        noteDAO.addNote(noteDetail)

    override suspend fun deleteNote(noteDetail: NoteModel) =
        noteDAO.deleteNote(noteDetail)

    override suspend fun updateNote(noteDetail: NoteModel) =
        noteDAO.updateNote(noteDetail)

    override fun getAllNotes(): Flow<List<NoteModel>> =
        noteDAO.getAllNotes()
}