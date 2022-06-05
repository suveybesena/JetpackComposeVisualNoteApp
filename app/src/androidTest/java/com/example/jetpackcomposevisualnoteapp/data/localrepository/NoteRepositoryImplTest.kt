package com.example.jetpackcomposevisualnoteapp.data.localrepository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import com.example.jetpackcomposevisualnoteapp.data.local.NoteDAO
import com.example.jetpackcomposevisualnoteapp.data.local.NoteDatabase
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NoteRepositoryImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var noteDatabase: NoteDatabase

    private lateinit var noteDAO: NoteDAO

    private lateinit var noteRepositoryImpl: NoteRepositoryImpl

    private var date = System.currentTimeMillis()
    private lateinit var noteDetail: NoteModel

    @Before
    fun setup() {
        hiltRule.inject()
        noteDAO = noteDatabase.noteDAO()
        noteRepositoryImpl = NoteRepositoryImpl(noteDAO)
        noteDetail = NoteModel("www.test.com", date, "noteTitle", "noteDesc", 1,1,"edited", 1)
        runBlockingTest {
            noteRepositoryImpl.addNote(noteDetail)
        }
    }

    @Test
    fun addNote() = runBlockingTest {
        val getFirstNote = noteRepositoryImpl.getAllNotes().first()[0]
        assertThat(noteDetail).isEqualTo(getFirstNote)
    }

    @Test
    fun deleteNote() = runBlockingTest {
        noteRepositoryImpl.deleteNote(noteDetail)
        val notesList = noteRepositoryImpl.getAllNotes().first()
        assertThat(notesList).doesNotContain(noteDetail)
    }
}