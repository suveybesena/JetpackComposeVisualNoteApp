package com.example.jetpackcomposevisualnoteapp.features.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposevisualnoteapp.common.Resource
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.domain.usecases.DeleteNoteFromLocalUseCase
import com.example.jetpackcomposevisualnoteapp.domain.usecases.GetAllNotesFromLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val getAllNotesFromLocalUseCase: GetAllNotesFromLocalUseCase,
    private val deleteNoteFromLocalUseCase: DeleteNoteFromLocalUseCase
) :
    ViewModel() {

    init {
        getAllNotes()
    }


    private val _uiState = mutableStateOf(NotesListUiState())
    val uiState: State<NotesListUiState> = _uiState

    fun handleEvent(event: NotesListUiEvent) {
        when (event) {
            is NotesListUiEvent.DeleteNote -> {
                deleteNote(event.noteDetail)
            }
            is NotesListUiEvent.GetAllNotes -> {
                getAllNotes()
            }

        }
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            getAllNotesFromLocalUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.value = uiState.value.copy(isLoading = resource.booelan)
                    }
                    is Resource.Success -> {
                        resource.data?.collect { list ->
                            _uiState.value = uiState.value.copy(notesList = list)
                        }
                    }
                    is Resource.Error -> {
                        _uiState.value = uiState.value.copy(error = resource.message)
                    }
                }
            }
        }
    }

    private fun deleteNote(noteDetail: NoteModel) {
        viewModelScope.launch {
            deleteNoteFromLocalUseCase.invoke(noteDetail).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _uiState.value = uiState.value.copy(error = resource.message)
                    }
                    is Resource.Loading -> {
                        _uiState.value = uiState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }
}