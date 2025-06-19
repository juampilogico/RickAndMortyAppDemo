package com.example.pruebasenrick.ui.screens.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.pruebasenrick.data.local.PersonajesLocal
import com.example.pruebasenrick.data.repository.FavoriteRepository
import com.example.pruebasenrick.ui.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen() {
    val context = LocalContext.current
    val viewModel = remember { FavoriteViewModel(FavoriteRepository(context)) }

    val favoritos by viewModel.favorites.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Favoritos",
                        color = Color(0xFF00FF00),
                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = 22.sp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1C1C1C))
            )
        },
        containerColor = Color(0xFF101010)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        color = Color(0xFF00FF00),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                favoritos.isEmpty() -> {
                    Text(
                        text = "No hay personajes favoritos.",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(favoritos, key = { it.id }) { personaje ->
                            FavoriteItemStyled(personaje) {
                                viewModel.removeFromFavorites(personaje)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteItemStyled(personaje: PersonajesLocal, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1F1F1F)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFF00FFAA), Color(0xFF0088FF))
                    )
                )
                .padding(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(Color(0xFF121212))
                    .padding(12.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = personaje.imageUrl),
                    contentDescription = personaje.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = personaje.name,
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
                    )
                    Text(
                        text = "Especie: ${personaje.species}",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Estado: ${personaje.status}",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = onDelete,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00FF00))
                    ) {
                        Text("Eliminar", color = Color.Black)
                    }
                }
            }
        }
    }
}

