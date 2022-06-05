package com.example.jetpackcomposevisualnoteapp.features.screen.drawing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposevisualnoteapp.ui.theme.graySurface
import kotlin.math.roundToInt
import kotlin.random.Random

@ExperimentalAnimationApi
@Composable
fun DrawingTools(
    drawColor: MutableState<Color>,
    drawBrush: MutableState<Float>,
    usedColors: MutableSet<Color>
) {
    var showBrushes by remember { mutableStateOf(false) }
    val strokes = remember { (1..50 step 5).toList() }

    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        ColorPicker(
            onColorSelected = { color ->
                drawColor.value = color
            })
        Row(
            modifier = Modifier
                .horizontalGradientBackground(listOf(graySurface, Color.Black))
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .horizontalScroll(rememberScrollState())
                .animateContentSize()
        ) {
            usedColors.forEach {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = null,
                    tint = it,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            drawColor.value = it
                        }
                )
            }
        }
        FloatingActionButton(
            onClick = { showBrushes = !showBrushes },
            modifier = Modifier.padding(vertical = 4.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Brush,
                contentDescription = null,
                tint = drawColor.value
            )
        }
        AnimatedVisibility(visible = showBrushes) {
            LazyColumn {
                items(strokes) {
                    IconButton(
                        onClick = {
                            drawBrush.value = it.toFloat()
                            showBrushes = false
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .border(
                                border = BorderStroke(
                                    width = with(LocalDensity.current) { it.toDp() },
                                    color = Color.Gray
                                ),
                                shape = CircleShape
                            )
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
fun ColorPicker(
    onColorSelected: (Color) -> Unit
) {
    Text(
        text = "Color picker with draggable",
        style = typography.subtitle2,
        modifier = Modifier.padding(8.dp)
    )
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenWidthInPx = with(LocalDensity.current) { screenWidth.toPx() }
    var activeColor by remember { mutableStateOf(Color.Red) }

    val max = screenWidth - 16.dp
    val min = 0.dp
    val (minPx, maxPx) = with(LocalDensity.current) { min.toPx() to max.toPx() }
    val dragOffset = remember { mutableStateOf(0f) }
    Box(modifier = Modifier.padding(8.dp)) {
        Spacer(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(brush = colorMapGradient(screenWidthInPx))
                .align(Alignment.Center)
                .pointerInput("painter") {
                    detectTapGestures { offset ->
                        dragOffset.value = offset.x
                        activeColor = getActiveColor(dragOffset.value, screenWidthInPx)
                        onColorSelected.invoke(activeColor)
                    }
                }
        )
        Icon(
            imageVector = Icons.Filled.FiberManualRecord,
            tint = activeColor,
            contentDescription = null,
            modifier = Modifier
                .offset { IntOffset(dragOffset.value.roundToInt(), 0) }
                .border(
                    border = BorderStroke(4.dp, MaterialTheme.colors.onSurface),
                    shape = CircleShape
                )
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        val newValue = dragOffset.value + delta
                        dragOffset.value = newValue.coerceIn(minPx, maxPx)
                        activeColor = getActiveColor(dragOffset.value, screenWidthInPx)
                        onColorSelected.invoke(activeColor)
                    }
                )
        )
    }
}


fun createColorMap(): List<Color> {
    val colorList = mutableListOf<Color>()
    for (i in 0..360 step (2)) {
        val randomSaturation = 90 + Random.nextFloat() * 10
        val randomLightness = 50 + Random.nextFloat() * 10
        val hsv = android.graphics.Color.HSVToColor(
            floatArrayOf(
                i.toFloat(),
                randomSaturation,
                randomLightness
            )
        )
        colorList.add(Color(hsv))
    }

    return colorList
}

fun colorMapGradient(screenWidthInPx: Float) = Brush.horizontalGradient(
    colors = createColorMap(),
    startX = 0f,
    endX = screenWidthInPx
)

fun getActiveColor(dragPosition: Float, screenWidth: Float): Color {
    val hue = (dragPosition / screenWidth) * 360f
    val randomSaturation = 90 + Random.nextFloat() * 10
    val randomLightness = 50 + Random.nextFloat() * 10
    return Color(
        android.graphics.Color.HSVToColor(
            floatArrayOf(
                hue,
                randomSaturation,
                randomLightness
            )
        )
    )
}

fun Modifier.horizontalGradientBackground(
    colors: List<Color>
) = gradientBackground(colors) { gradientColors, size ->
    Brush.horizontalGradient(
        colors = gradientColors,
        startX = 0f,
        endX = size.width
    )
}

fun Modifier.gradientBackground(
    colors: List<Color>,
    brushProvider: (List<Color>, Size) -> Brush
): Modifier = composed {
    var size by remember { mutableStateOf(Size.Zero) }
    val gradient = remember(colors, size) { brushProvider(colors, size) }
    drawWithContent {
        size = this.size
        drawRect(brush = gradient)
        drawContent()
    }
}