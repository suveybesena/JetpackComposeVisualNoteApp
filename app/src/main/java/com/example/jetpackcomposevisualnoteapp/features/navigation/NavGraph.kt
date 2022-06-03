package com.example.jetpackcomposevisualnoteapp.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposevisualnoteapp.common.Constants.PARCELABLE_ARGS_ID
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.features.screen.addnote.NoteAddScreen
import com.example.jetpackcomposevisualnoteapp.features.screen.editnote.NoteEditScreen
import com.example.jetpackcomposevisualnoteapp.features.screen.home.NoteListScreen
import com.example.jetpackcomposevisualnoteapp.features.screen.splash.AnimatedSplashScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screen.NoteListScreen.route) {
            NoteListScreen(navController = navController)
        }
        composable(route = Screen.NoteAddScreen.route) {
            NoteAddScreen(navController = navController)
        }
        composable(route = Screen.NoteEditScreen.route) {
            val noteArg =
                navController.previousBackStackEntry?.savedStateHandle?.get<NoteModel>(
                    PARCELABLE_ARGS_ID
                )
            if (noteArg != null) {
                NoteEditScreen(navController = navController, noteArg)
            }
        }
    }
}