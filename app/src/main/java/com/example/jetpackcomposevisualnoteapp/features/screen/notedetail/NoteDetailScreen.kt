package com.example.jetpackcomposevisualnoteapp.features.screen.notedetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposevisualnoteapp.R
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.features.components.NoteImage

@Composable
fun NoteDetailScreen(
    navArg: NoteModel
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteImage(
                noteImageUrl = navArg.imageUrl,
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(5.dp)
                    .clip(shape = RoundedCornerShape(15)),
                placeholder = R.drawable.ic_error,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(10.dp))
            navArg.noteTitle?.let {
                Text(
                    text = it,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            navArg.noteDesc?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .padding(2.dp),
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}