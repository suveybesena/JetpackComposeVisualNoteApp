package com.example.jetpackcomposevisualnoteapp.features.screen.addnote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jetpackcomposevisualnoteapp.MainCoroutineRule
import com.example.jetpackcomposevisualnoteapp.common.Constants
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.data.repository.FakeNoteRepositoryImpl
import com.example.jetpackcomposevisualnoteapp.domain.usecases.AddNoteToLocalUseCase
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
class AddNoteViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var addNoteViewModel: AddNoteViewModel

    private val date = System.currentTimeMillis()

    @Before
    fun setup() {
        addNoteViewModel =
            AddNoteViewModel(AddNoteToLocalUseCase(FakeNoteRepositoryImpl(), testDispatcher))
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `add note without noteTitle returns error`() = runBlockingTest {
        val noteDetail = NoteModel("www.test.com", date, "", "noteDesc", 1, 2, id = 1)
        addNoteViewModel.handleEvent(AddNoteUiEvent.AddNote(noteDetail))
        val state = addNoteViewModel.uiState.value.error
        assertThat(state).isEqualTo(Constants.NULL_ERROR_MESSAGE)
    }

    @Test
    fun `add note without noteDesc returns error`() = runBlockingTest {
        val noteDetail = NoteModel("www.test.com", date, "noteTitle", "", 1, 2, id = 1)
        addNoteViewModel.handleEvent(AddNoteUiEvent.AddNote(noteDetail))
        val state = addNoteViewModel.uiState.value.error
        assertThat(state).isEqualTo(Constants.NULL_ERROR_MESSAGE)
    }

    @Test
    fun `add note without noteImage returns error`() = runBlockingTest {
        val noteDetail = NoteModel("", date, "noteTitle", "noteDesc", 1, 2, id = 1)
        addNoteViewModel.handleEvent(AddNoteUiEvent.AddNote(noteDetail))
        val state = addNoteViewModel.uiState.value.error
        assertThat(state).isEqualTo(Constants.NULL_ERROR_MESSAGE)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}