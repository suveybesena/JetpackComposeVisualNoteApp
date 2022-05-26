package com.example.jetpackcomposevisualnoteapp.domain.usecases

import com.example.jetpackcomposevisualnoteapp.common.Constants
import com.example.jetpackcomposevisualnoteapp.common.Resource
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.di.IoDispatcher
import com.example.jetpackcomposevisualnoteapp.domain.localrepository.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddNoteToLocalUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun invoke(noteDetail: NoteModel) = flow {
        emit(Resource.Loading)
        try {
            if (noteDetail.noteTitle.isNullOrBlank() || noteDetail.noteDesc.isNullOrBlank() || noteDetail.imageUrl.isNullOrBlank()) {
                emit(Resource.Error(Constants.NULL_ERROR_MESSAGE))
            } else {
                val addNote = noteRepository.addNote(noteDetail)
                emit(Resource.Success(addNote))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(ioDispatcher)
}