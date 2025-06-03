package com.example.pruebasenrick.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pruebasenrick.ui.screens.characterslist.CharactersListScreen
import com.example.pruebasenrick.ui.screens.characterdetail.CharacterDetailScreen
import com.example.pruebasenrick.ui.screens.login.LoginScreen
import com.example.pruebasenrick.ui.screens.splash.SplashScreen

@Composable
fun NavigationStack(
    onGoogleLoginClick: () -> Unit,
    navController: NavHostController,
    onLogoutClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(Screens.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screens.Login.route) {
            LoginScreen(
                onGoogleLoginClick = onGoogleLoginClick,
                navController = navController
            )
        }
        composable(Screens.CharacterList.route) {
            CharactersListScreen(
                navController = navController,
                onLogoutClick = onLogoutClick
            )
        }
        composable(Screens.CharacterDetail.route) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
            characterId?.let {
                CharacterDetailScreen(characterId = it, navController = navController)
            }
        }
    }
}

