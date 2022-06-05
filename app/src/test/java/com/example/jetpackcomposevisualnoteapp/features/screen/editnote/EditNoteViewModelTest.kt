package com.example.jetpackcomposevisualnoteapp.features.screen.editnote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jetpackcomposevisualnoteapp.MainCoroutineRule
import com.example.jetpackcomposevisualnoteapp.common.Resource
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.data.repository.FakeNoteRepositoryImpl
import com.example.jetpackcomposevisualnoteapp.domain.usecases.DeleteNoteFromLocalUseCase
import com.example.jetpackcomposevisualnoteapp.domain.usecases.UpdateNoteFromLocalUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EditNoteViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var editNoteViewModel: NoteEditViewModel

    private val date = System.currentTimeMillis()

    @Before
    fun setup() {
        editNoteViewModel =
            NoteEditViewModel(
                DeleteNoteFromLocalUseCase(FakeNoteRepositoryImpl(), testDispatcher),
                UpdateNoteFromLocalUseCase(FakeNoteRepositoryImpl(), testDispatcher),
            )
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `delete note without noteId returns error`() = runBlockingTest {
        val noteDetail =
            NoteModel("www.test.com", date, "noteTitle", "noteDesc", 1, 2, editedTag = "edited")
        editNoteViewModel.handleEvent(EditNoteUiEvent.UpdateNote(noteDetail))
        val state = editNoteViewModel.uiState.value.error
        assertThat(state).isNotEqualTo(Resource.Error<String>())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}