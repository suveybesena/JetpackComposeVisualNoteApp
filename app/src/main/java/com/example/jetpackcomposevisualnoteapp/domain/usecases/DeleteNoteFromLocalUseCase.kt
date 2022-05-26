package com.example.jetpackcomposevisualnoteapp.domain.usecases

import com.example.jetpackcomposevisualnoteapp.common.Resource
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.di.IoDispatcher
import com.example.jetpackcomposevisualnoteapp.domain.localrepository.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeleteNoteFromLocalUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    fun invoke(noteDetail: NoteModel) = flow {
        emit(Resource.Loading)
        try {
            val deleteNote = noteRepository.deleteNote(noteDetail)
            emit(Resource.Success(deleteNote))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(ioDispatcher)
}