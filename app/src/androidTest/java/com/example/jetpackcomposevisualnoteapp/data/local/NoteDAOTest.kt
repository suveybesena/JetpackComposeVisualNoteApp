package com.example.jetpackcomposevisualnoteapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NoteDAOTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var noteDatabase: NoteDatabase

    private lateinit var noteDAO: NoteDAO

    private lateinit var noteDetail: NoteModel

    @Before
    fun setup() {
        hiltRule.inject()
        noteDAO = noteDatabase.noteDAO()
        val date = System.currentTimeMillis()
        noteDetail = NoteModel("www.test.com", date, "Title", "Desc", 1, 1, "edited", 1)
    }

    @Test
    fun insertNoteTesting() = runBlockingTest {
        noteDAO.addNote(noteDetail)
        val list = noteDAO.getAllNotes().first()
        assertThat(list).contains(noteDetail)
    }

    @Test
    fun deleteNoteTesting() = runBlockingTest {
        noteDAO.addNote(noteDetail)
        noteDAO.deleteNote(noteDetail)
        val list = noteDAO.getAllNotes().first()
        assertThat(list).doesNotContain(noteDetail)
    }

    @After
    fun teardown() {
        noteDatabase.close()
    }
}