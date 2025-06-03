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

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = character.name, color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Volver",
                                tint = Color.White
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
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imagen del personaje
                Card(
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                ) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = character.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Tarjeta con detalles divididos en dos columnas
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            CharacterDetailRow(Icons.Default.Info, "Status", character.status)
                            CharacterDetailRow(Icons.Default.Person, "Species", character.species)
                            CharacterDetailRow(Icons.Default.Info, "Type", character.type.ifBlank { "None" })
                            CharacterDetailRow(Icons.Default.Face, "Gender", character.gender)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            CharacterDetailRow(Icons.Default.Place, "Origin", character.origin.name)
                            CharacterDetailRow(Icons.Default.Place, "Last Location", character.location.name)
                            CharacterDetailRow(Icons.Default.List, "Episodes", character.episode.size.toString())
                            CharacterDetailRow(Icons.Default.Info, "Created", character.created.substringBefore("T"))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterDetailRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color(0xFF4CAF50),
            modifier = Modifier
                .size(26.dp)
                .padding(end = 12.dp)
        )
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.LightGray,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                )
            )
        }
    }
}