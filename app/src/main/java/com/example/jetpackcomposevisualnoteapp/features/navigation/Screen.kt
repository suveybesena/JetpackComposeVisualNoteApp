package com.example.jetpackcomposevisualnoteapp.features.navigation

sealed class Screen(val route: String) {
    object NoteListScreen : Screen("note_list_screen")
    object NoteEditScreen : Screen("note_edit_screen")
    object NoteAddScreen : Screen("note_add_screen")
    object Splash : Screen("splash_screen")
}
