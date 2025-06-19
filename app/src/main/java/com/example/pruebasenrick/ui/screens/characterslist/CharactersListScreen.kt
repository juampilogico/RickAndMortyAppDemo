package com.example.pruebasenrick.ui.screens.characterslist

import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.Glide
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import com.example.pruebasenrick.ui.screens.Screens
import com.example.pruebasenrick.data.Character
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment

@Composable
fun CharactersListScreen(
    modifier: Modifier = Modifier,
    vm: CharacterListScreenViewModel = viewModel(),
    navController: NavHostController,
    onLogoutClick: () -> Unit
) {
    val state = vm.uiState

    Scaffold(
        containerColor = Color(0xFF1B1F23)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onLogoutClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("Logout")
                }
            }

            Text(
                text = "Rick & Morty Characters",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50)
                ),
                modifier = modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = state.searchQuery,
                    onValueChange = { vm.onSearchChange(it) },
                    label = { Text("Buscar personaje") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color(0xFF4CAF50),
                        unfocusedIndicatorColor = Color.Gray,
                        focusedContainerColor = Color.DarkGray,
                        unfocusedContainerColor = Color.DarkGray,
                        cursorColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.LightGray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { vm.fetchCharacters() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("Buscar")
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.filteredList, key = { it.id }) { character ->
                    CharacterItem(character = character) {
                        navController.navigate(Screens.CharacterDetail.createRoute(character.id))
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: Character, onClick: () -> Unit) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }
            .shadow(8.dp, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            AndroidView(
                factory = {
                    ImageView(it).apply {
                        layoutParams = ViewGroup.LayoutParams(200, 200)
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        Glide.with(it)
                            .load(character.image)
                            .into(this)
                    }
                },
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = character.species,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.LightGray
                    )
                )
            }
        }
    }
}
