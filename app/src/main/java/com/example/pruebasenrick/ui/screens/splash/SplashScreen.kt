package com.example.pruebasenrick.ui.screens.splash

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.pruebasenrick.ui.screens.Screens
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect
import android.view.ViewGroup
import androidx.compose.ui.draw.clip



@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(Screens.Login.route) {
            popUpTo(Screens.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { context ->
                ImageView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    scaleType = ImageView.ScaleType.FIT_CENTER
                    Glide.with(context)
                        .load("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Rick_and_Morty.svg/640px-Rick_and_Morty.svg.png")
                        .into(this)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
