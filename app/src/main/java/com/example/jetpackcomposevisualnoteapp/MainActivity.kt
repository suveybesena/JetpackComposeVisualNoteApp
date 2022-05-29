package com.example.jetpackcomposevisualnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposevisualnoteapp.presentation.addnote.NoteAddScreen
import com.example.jetpackcomposevisualnoteapp.presentation.edit.NoteEditScreen
import com.example.jetpackcomposevisualnoteapp.presentation.notelist.NoteListScreen
import com.example.jetpackcomposevisualnoteapp.ui.theme.JetpackComposeVisualNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeVisualNoteAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "note_list_screen") {
                    composable("note_list_screen") {
                        NoteListScreen(navController = navController)
                    }
                    composable("note_add_screen") {
                        NoteAddScreen(navController = navController)
                    }
                    composable("note_edit_screen") {
                        NoteEditScreen(navController = navController)
                    }
                    //composable("note_edit_screen/{noteArg}", arguments = listOf(
                    //    navArgument("noteArg") {
                    //        type = NavType.ParcelableType(NoteModel::class.java)
                    //    }
                    //)) {
                    // val noteArg = it.arguments?.getParcelable<NoteModel>("noteArg")
                    //if (noteArg != null) {
                    //    NoteEditScreen(noteArg = noteArg)
                    //}
                    //}
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeVisualNoteAppTheme {
        Greeting("Android")
    }
}