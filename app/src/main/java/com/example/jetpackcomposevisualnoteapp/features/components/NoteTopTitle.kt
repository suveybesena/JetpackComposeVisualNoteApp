package com.example.jetpackcomposevisualnoteapp.features.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposevisualnoteapp.ui.theme.Color

@Composable
fun NoteTopTitle(string: String) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .shadow(5.dp)
            .height(68.dp)
            .clip(shape = RoundedCornerShape(15))
            .background(Color.Blue)

    ) {
        Row() {
            Text(
                string, modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                textAlign = TextAlign.Start,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = androidx.compose.ui.graphics.Color.White
            )
        }
    }
}