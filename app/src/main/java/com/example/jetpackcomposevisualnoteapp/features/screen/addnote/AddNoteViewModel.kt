package com.example.jetpackcomposevisualnoteapp.features.screen.addnote

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposevisualnoteapp.common.Resource
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.domain.usecases.AddNoteToLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val addNoteToLocalUseCase: AddNoteToLocalUseCase) :
    ViewModel() {

    private val _uiState = mutableStateOf(AddNoteUiState())
    val uiState: State<AddNoteUiState> = _uiState

    fun handleEvent(event: AddNoteUiEvent) {
        when (event) {
            is AddNoteUiEvent.AddNote -> {
                addNote(event.noteDetail)
            }
        }
    }

    private fun addNote(noteDetail: NoteModel) {
        viewModelScope.launch {
            addNoteToLocalUseCase.invoke(noteDetail).collect { resource ->
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