package com.example.pruebasenrick.ui.screens.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pruebasenrick.ui.screens.Screens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onGoogleLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    vm: LoginScreenViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        vm.uiEvent.collect { event ->
            navController.navigate(Screens.CharacterList.route) {
                popUpTo(Screens.Login.route) { inclusive = true }
            }
        }
    }

    var isVisible by remember { mutableStateOf(false) }
    var isPressed by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Animación de visibilidad
    LaunchedEffect(Unit) {
        delay(300)
        isVisible = true
    }

    // Escala animada para efecto rebote
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 100)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F0C29), // fondo oscuro
                        Color(0xFF302B63),
                        Color(0xFF43FF64)  // verde portal
                    )
                )
            )
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(tween(600)) + scaleIn(initialScale = 0.8f, animationSpec = tween(600))
            ) {
                Text(
                    text = "Rick & Morty App",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(tween(800))
            ) {
                Text(
                    text = "¡Descubrí personajes y guardá tus favoritos del multiverso!",
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.85f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(tween(1000)) + scaleIn(initialScale = 0.9f, animationSpec = tween(1000))
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            isPressed = true
                            delay(100) // rebote corto
                            isPressed = false
                            delay(100)
                            onGoogleLoginClick()
                        }
                    },
                    shape = RoundedCornerShape(40),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .scale(scale)
                ) {
                    Text(
                        text = "Ingresar con Google",
                        fontSize = 16.sp,
                        letterSpacing = 1.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(tween(1200))
            ) {
            }
        }
    }
}



