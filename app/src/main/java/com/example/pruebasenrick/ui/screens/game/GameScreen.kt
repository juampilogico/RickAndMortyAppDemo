package com.example.pruebasenrick.ui.screens.game


import com.example.pruebasenrick.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.pruebasenrick.data.Character
import com.example.pruebasenrick.ui.screens.Screens
import android.media.MediaPlayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.font.FontWeight






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    navController: NavHostController,
    character: Character,
    onAnswer: (Boolean) -> Unit,
    result: String?,
    onNext: () -> Unit
) {
    // 🎵 Música de fondo
    val context = LocalContext.current
    var isMuted by remember { mutableStateOf(false) }
    val mediaPlayer: MediaPlayer = remember {
        MediaPlayer.create(context, R.raw.intro).apply {
            isLooping = true
            start()
        }
    }

    LaunchedEffect(isMuted) {
        if (isMuted) mediaPlayer.setVolume(0f, 0f)
        else mediaPlayer.setVolume(1f, 1f)
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {}, //
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (!navController.popBackStack()) {
                                navController.navigate(Screens.CharacterList.route)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { isMuted = !isMuted }) {
                        Icon(
                            imageVector = Icons.Filled.Face,
                            contentDescription = "Sonido",
                            tint = if (isMuted) Color.Gray else Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1C1C1C)
                )
            )
        },
        containerColor = Color(0xFF121212)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFF121212)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier
                    .size(250.dp)
                    .padding(16.dp)
                    .border(4.dp, Color(0xFF00FFAA), RoundedCornerShape(16.dp))
            )

            Text(
                text = character.name,
                color = Color.White,
                fontSize = 28.sp,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Button(
                    onClick = { onAnswer(true) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00FFAA),
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Vivo", fontSize = 18.sp)
                }

                Button(
                    onClick = { onAnswer(false) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF4081),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Muerto", fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            result?.let {
                Text(
                    text = it,
                    color = if (it.contains("¡Correcto!")) Color(0xFF00FFAA) else Color.Red,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onNext,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF03DAC6),
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Siguiente personaje", fontSize = 16.sp)
                }
            }
        }
    }
}

