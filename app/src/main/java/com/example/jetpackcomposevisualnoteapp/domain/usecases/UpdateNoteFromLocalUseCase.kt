package com.example.jetpackcomposevisualnoteapp.domain.usecases

import com.example.jetpackcomposevisualnoteapp.common.Resource
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.di.IoDispatcher
import com.example.jetpackcomposevisualnoteapp.domain.localrepository.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateNoteFromLocalUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun invoke(noteDetail: NoteModel) = flow {
        emit(Resource.Loading(true))
        try {
            val updateNote = noteRepository.updateNote(noteDetail)
            emit(Resource.Success(updateNote))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(ioDispatcher)
}