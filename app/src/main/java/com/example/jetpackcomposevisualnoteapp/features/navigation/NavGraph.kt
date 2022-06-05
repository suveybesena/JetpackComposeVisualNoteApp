package com.example.jetpackcomposevisualnoteapp.features.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposevisualnoteapp.common.Constants.PARCELABLE_ARGS_ID
import com.example.jetpackcomposevisualnoteapp.common.Constants.STRING_ARGS_ID
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.features.screen.addnote.NoteAddScreen
import com.example.jetpackcomposevisualnoteapp.features.screen.drawing.DrawingScreen
import com.example.jetpackcomposevisualnoteapp.features.screen.editnote.NoteEditScreen
import com.example.jetpackcomposevisualnoteapp.features.screen.home.NoteListScreen
import com.example.jetpackcomposevisualnoteapp.features.screen.main.MainActivity
import com.example.jetpackcomposevisualnoteapp.features.screen.notedetail.NoteDetailScreen
import com.example.jetpackcomposevisualnoteapp.features.screen.splash.AnimatedSplashScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(
    ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class,
    ExperimentalPermissionsApi::class
)
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
        composable(route = Screen.DrawingScreen.route) {
            DrawingScreen(navController = navController)
        }
        composable(route = Screen.NoteDetailScreen.route) {
            val noteArg =
                navController.previousBackStackEntry?.savedStateHandle?.get<NoteModel>(
                    PARCELABLE_ARGS_ID
                )
            if (noteArg != null) {
                NoteDetailScreen(noteArg)
            }
        }
        composable(route = Screen.NoteAddScreen.route) {
            val noteArg =
                navController.previousBackStackEntry?.savedStateHandle?.get<String>(
                    STRING_ARGS_ID
                )
            if (noteArg != null) {
                NoteAddScreen(noteArg, navController = navController)
            }
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