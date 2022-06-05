package com.example.jetpackcomposevisualnoteapp.features.screen.drawing


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Screenshot
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.jetpackcomposevisualnoteapp.common.Constants.STRING_ARGS_ID
import com.example.jetpackcomposevisualnoteapp.features.navigation.Screen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.CaptureController
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import java.io.ByteArrayOutputStream


@ExperimentalPermissionsApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MutableCollectionMutableState")
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun DrawingScreen(navController: NavController) {

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
        } else {
        }
    }
    val context = LocalContext.current

    val captureController = rememberCaptureController()
    val paths = remember { mutableStateOf(mutableListOf<PathState>()) }

    Capturable(
        controller = captureController,
        onCaptured = { imageBitmap, error ->
            if (imageBitmap != null) {
                val bytes = ByteArrayOutputStream()
                imageBitmap.asAndroidBitmap().compress(Bitmap.CompressFormat.JPEG, 100, bytes)
                val path: String = MediaStore.Images.Media.insertImage(
                    context.getContentResolver(),
                    imageBitmap.asAndroidBitmap(),
                    "Title",
                    null
                )
                val uri = Uri.parse(path).toString()
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    STRING_ARGS_ID,
                    uri
                )
                navController.navigate(Screen.NoteAddScreen.route)
            }
        }
    ) {
        val drawColor = remember { mutableStateOf(Color.Black) }
        val drawBrush = remember { mutableStateOf(5f) }
        val usedColors =
            remember { mutableStateOf(mutableSetOf(Color.Black, Color.White, Color.Gray)) }
        paths.value.add(PathState(Path(), drawColor.value, drawBrush.value))
        DrawingCanvas(
            drawColor = drawColor,
            drawBrush = drawBrush,
            usedColors = usedColors,
            paths = paths.value
        )
    }

    Scaffold(
        topBar = {
            ComposePaintAppBar(launcher, context, captureController) {
                paths.value = mutableListOf()
            }
        }
    ) {
        PaintBody(paths)
    }
}

@Composable
fun ComposePaintAppBar(
    launcher: ManagedActivityResultLauncher<String, Boolean>,
    context: Context,
    captureController: CaptureController,
    onDelete: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = "Canvas"
            )
        },
        actions = {
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
            IconButton(onClick = {
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) -> {
                        captureController.capture()
                    }
                    else -> {
                        launcher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Screenshot,
                    contentDescription = "Capture"
                )
            }
        }
    )
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun PaintBody(
    paths: MutableState<MutableList<PathState>>
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val drawColor = remember { mutableStateOf(Color.Black) }
        val drawBrush = remember { mutableStateOf(5f) }
        val usedColors =
            remember { mutableStateOf(mutableSetOf(Color.Black, Color.White, Color.Gray)) }

        paths.value.add(PathState(Path(), drawColor.value, drawBrush.value))

        DrawingCanvas(
            drawColor,
            drawBrush,
            usedColors,
            paths.value
        )
        DrawingTools(
            drawColor = drawColor,
            drawBrush = drawBrush,
            usedColors = usedColors.value
        )
    }
}


