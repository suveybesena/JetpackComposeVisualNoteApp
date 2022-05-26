package com.example.jetpackcomposevisualnoteapp.di

import com.example.jetpackcomposevisualnoteapp.data.localrepository.NoteRepositoryImpl
import com.example.jetpackcomposevisualnoteapp.domain.localrepository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNoteRepository(noteRepositoryImpl: NoteRepositoryImpl): NoteRepository
}