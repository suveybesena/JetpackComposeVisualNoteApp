package com.example.jetpackcomposevisualnoteapp.domain.usecases

import com.example.jetpackcomposevisualnoteapp.common.Resource
import com.example.jetpackcomposevisualnoteapp.di.IoDispatcher
import com.example.jetpackcomposevisualnoteapp.domain.localrepository.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllNotesFromLocalUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun invoke() = flow {
        emit(Resource.Loading(true))
        kotlinx.coroutines.delay(1000)
        emit(Resource.Loading(false))
        try {
            val allNotes = noteRepository.getAllNotes()
            emit(Resource.Success(allNotes))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(ioDispatcher)
}