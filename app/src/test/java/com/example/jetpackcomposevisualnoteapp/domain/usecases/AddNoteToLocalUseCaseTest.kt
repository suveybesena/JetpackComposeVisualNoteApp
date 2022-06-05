package com.example.jetpackcomposevisualnoteapp.domain.usecases


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jetpackcomposevisualnoteapp.MainCoroutineRule
import com.example.jetpackcomposevisualnoteapp.common.Resource
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.data.repository.FakeNoteRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AddNoteToLocalUseCaseTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var addNoteToLocalUseCase: AddNoteToLocalUseCase
    private lateinit var fakeRepository: FakeNoteRepositoryImpl

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeNoteRepositoryImpl()
        addNoteToLocalUseCase = AddNoteToLocalUseCase(fakeRepository, testDispatcher)
    }

    @Test
    fun addNote_ShouldReturnLoadingThenResource_collectIndexed() {
        runBlockingTest {
            val date = System.currentTimeMillis()
            val noteDetail = NoteModel("www.test.com", date, "Title", "Desc", 1, 1, "edited", 1)
            addNoteToLocalUseCase.invoke(noteDetail).collectIndexed { index, value ->
                if (index == 0) assert(value is Resource.Loading)
                if (index == 1) assert(value is Resource.Success)
                if (index == 2) assert(value is Resource.Error)
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}