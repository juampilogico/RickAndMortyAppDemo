package com.example.pruebasenrick.ui.screens.favorites




import com.example.pruebasenrick.ui.screens.Screens
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pruebasenrick.ui.screens.characterslist.CharactersListScreen
import com.example.pruebasenrick.ui.screens.characterdetail.CharacterDetailScreen
import com.example.pruebasenrick.ui.screens.login.LoginScreen
import com.example.pruebasenrick.ui.screens.splash.SplashScreen
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pruebasenrick.ui.screens.favorites.FavoritesScreen
import com.example.pruebasenrick.ui.screens.game.GameScreen
import com.example.pruebasenrick.ui.screens.game.GameViewModel
import androidx.compose.runtime.collectAsState



@Composable
fun NavigationStack(
    onGoogleLoginClick: () -> Unit,
    navController: NavHostController,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier // ✅ LO AGREGÁS ACÁ
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route,
        modifier = modifier // ✅ SE USA ACÁ
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
        composable(route = Screens.Favorites.route) {
            FavoritesScreen()
        }
        composable(route = Screens.Game.route) {
            val vm: GameViewModel = viewModel()
            val character = vm.character.collectAsState().value
            val result = vm.result.collectAsState().value

            character?.let {
                GameScreen(
                    navController = navController,
                    character = it,
                    result = result,
                    onAnswer = { isAlive -> vm.checkAnswer(isAlive) },
                    onNext = { vm.getRandomCharacter() }
                )
            } ?: run {
                // 🌀 Loader si el personaje aún no fue cargado
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }

}
