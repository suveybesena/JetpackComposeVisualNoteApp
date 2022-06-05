package com.example.jetpackcomposevisualnoteapp.features.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jetpackcomposevisualnoteapp.features.navigation.NavGraph
import com.example.jetpackcomposevisualnoteapp.ui.theme.JetpackComposeVisualNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeVisualNoteAppTheme {
                NavGraph()
            }
        }
    }
}
