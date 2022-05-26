package com.example.jetpackcomposevisualnoteapp.di

import android.content.Context
import androidx.room.Room
import com.example.jetpackcomposevisualnoteapp.common.Constants
import com.example.jetpackcomposevisualnoteapp.data.local.NoteDAO
import com.example.jetpackcomposevisualnoteapp.data.local.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideNoteDAO(noteDatabase: NoteDatabase): NoteDAO =
        noteDatabase.noteDAO()
}