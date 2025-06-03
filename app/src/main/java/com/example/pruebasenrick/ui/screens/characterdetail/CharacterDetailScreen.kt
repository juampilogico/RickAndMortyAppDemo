@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.pruebasenrick.ui.screens.characterdetail


import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.example.pruebasenrick.data.Character


@Composable
fun CharacterDetailScreen(
    characterId: Int,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    vm: CharacterDetailScreenViewModel = viewModel()
) {
    vm.setCharacterId(characterId)

    if (vm.uiState.characterDetail.id == 0) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val character = vm.uiState.characterDetail

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Nombre: ${character.name}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Estado: ${character.status}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Especie: ${character.species}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Género: ${character.gender}")
        }
    }
}
