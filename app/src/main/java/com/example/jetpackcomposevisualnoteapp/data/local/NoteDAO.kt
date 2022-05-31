package com.example.jetpackcomposevisualnoteapp.data.local

import androidx.room.*
import com.example.jetpackcomposevisualnoteapp.common.Constants
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(noteDetail: NoteModel)

    @Update
    suspend fun updateNote(noteDetail: NoteModel)

    @Delete
    suspend fun deleteNote(noteDetail: NoteModel)

    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
    fun getAllNotes(): Flow<List<NoteModel>>
}