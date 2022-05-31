package com.example.jetpackcomposevisualnoteapp.presentation.notelist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposevisualnoteapp.common.Resource
import com.example.jetpackcomposevisualnoteapp.domain.usecases.GetAllNotesFromLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(private val getAllNotesFromLocalUseCase: GetAllNotesFromLocalUseCase) :
    ViewModel() {

    private val _uiState = mutableStateOf(NotesListUiState())
    val uiState: State<NotesListUiState> = _uiState

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            getAllNotesFromLocalUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.data?.collect { list ->
                            _uiState.value = uiState.value.copy(notesList = list)
                        }
                    }
                    is Resource.Loading -> {
                        _uiState.value = uiState.value.copy(isLoading = true)
                    }
                    is Resource.Error -> {
                        _uiState.value = uiState.value.copy(error = resource.message)
                    }
                }
            }
        }
    }
}