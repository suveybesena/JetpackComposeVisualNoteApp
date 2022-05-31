package com.example.jetpackcomposevisualnoteapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDAO(): NoteDAO
}