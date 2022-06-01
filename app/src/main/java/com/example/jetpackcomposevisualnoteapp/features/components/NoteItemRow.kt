package com.example.jetpackcomposevisualnoteapp.features.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposevisualnoteapp.R
import com.example.jetpackcomposevisualnoteapp.data.model.NoteModel
import com.example.jetpackcomposevisualnoteapp.features.components.NoteImage
import com.example.jetpackcomposevisualnoteapp.ui.theme.Color

@ExperimentalFoundationApi
@Composable
fun NoteItemRow(
    noteDetail: NoteModel,
    onNoteClicked: (NoteModel?) -> Unit,
) {
    var isShortText by remember { mutableStateOf(false) }
    val shortText = "Long press for detailed view +"
    val longText = noteDetail.noteDesc

    var isGray by remember { mutableStateOf(false) }
    val animateColor = animateColorAsState(
        if (isGray) Color.Blue else androidx.compose.ui.graphics.Color.White,
        animationSpec = tween(durationMillis = 1500)
    )

    Card(modifier = Modifier.padding(5.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(animateColor.value)
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = { isGray = !isGray },
                    onTap = { onNoteClicked(noteDetail) },
                    onLongPress = { isShortText = !isShortText }
                )
            }
        ) {
            NoteImage(
                noteImageUrl = noteDetail.imageUrl,
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
                    .padding(5.dp)
                    .clip(shape = RoundedCornerShape(15)),
                placeholder = R.drawable.ic_error,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box {
                Column {
                    noteDetail.noteTitle?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(2.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    noteDetail.noteDesc?.let {
                        (if (isShortText) longText else shortText)?.let { noteDesc ->
                            Text(
                                text = noteDesc,
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(2.dp),
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }
    }
}