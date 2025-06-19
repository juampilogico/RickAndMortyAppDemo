package com.example.pruebasenrick.ui.screens.characterdetail


import com.example.pruebasenrick.ui.components.CharacterDetailRow
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.pruebasenrick.data.local.PersonajesLocal
import com.example.pruebasenrick.data.repository.FavoriteRepository
import com.example.pruebasenrick.ui.FavoriteViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    vm: CharacterDetailScreenViewModel = viewModel()
) {
    val context = LocalContext.current
    val favoriteVm = remember { FavoriteViewModel(FavoriteRepository(context)) }

    vm.setCharacterId(characterId)

    val character = vm.uiState.characterDetail
    val favoritos by favoriteVm.favorites.collectAsState()
    val isFavorite = favoritos.any { it.id == character.id }

    LaunchedEffect(Unit) {
        favoriteVm.loadFavorites()
    }

    if (character.id == 0) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = character.name, color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Volver",
                                tint = Color.Red
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            val personajeLocal = PersonajesLocal(
                                id = character.id,
                                userUid = FirebaseAuth.getInstance().currentUser?.uid ?: "",
                                name = character.name,
                                status = character.status,
                                species = character.species,
                                type = character.type,
                                gender = character.gender,
                                origin = character.origin,
                                location = character.location,
                                imageUrl = character.image,
                                episode = character.episode,
                                created = character.created
                            )

                            if (isFavorite) {
                                favoriteVm.removeFromFavorites(personajeLocal)
                            } else {
                                favoriteVm.addToFavorites(personajeLocal)
                            }
                        }) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorito",
                                tint = Color.Yellow
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
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
