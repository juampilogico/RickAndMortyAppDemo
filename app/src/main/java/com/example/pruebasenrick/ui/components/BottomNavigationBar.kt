package com.example.pruebasenrick.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pruebasenrick.ui.screens.Screens
import androidx.compose.ui.graphics.Color


data class BottomNavItem(val route: String, val icon: ImageVector)

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem(Screens.CharacterList.route, Icons.Filled.Home),
        BottomNavItem("favorites", Icons.Filled.FavoriteBorder),
        BottomNavItem("minigame", Icons.Filled.Info)
    )

    NavigationBar(
        containerColor = Color(0xFF1C1C1C),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.route
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    if (item.route == Screens.CharacterList.route) {
                        navController.navigate(Screens.CharacterList.route) {
                            popUpTo(0)
                        }
                    }
                }
            )
        }
    }
}
