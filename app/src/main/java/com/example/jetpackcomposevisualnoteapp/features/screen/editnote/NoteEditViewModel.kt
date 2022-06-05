package com.example.jetpackcomposevisualnoteapp.features.screen.editnote

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposevisualnoteapp.common.Resource
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.domain.usecases.UpdateNoteFromLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteEditViewModel @Inject constructor(
    private val updateNoteFromLocalUseCase: UpdateNoteFromLocalUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(EditNoteUiState())
    val uiState: State<EditNoteUiState> = _uiState

    fun handleEvent(event: EditNoteUiEvent) {
        when (event) {
            is EditNoteUiEvent.UpdateNote -> {
                updateNote(event.noteDetail)
            }
        }
    }

    private fun updateNote(noteDetail: NoteModel) {
        viewModelScope.launch {
            updateNoteFromLocalUseCase.invoke(noteDetail).collect { resource ->
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