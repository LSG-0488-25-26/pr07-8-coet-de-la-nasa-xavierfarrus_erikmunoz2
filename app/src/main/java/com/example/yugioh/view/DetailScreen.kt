package com.example.yugioh.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.yugioh.model.YugiohCard
import com.example.yugioh.viewmodel.FavoritesViewModel
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    card: YugiohCard?,
    paddingValues: PaddingValues,
    windowSizeClass: WindowSizeClass,
    favoritesViewModel: FavoritesViewModel
) {
    if (card == null) {
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
            Text("Carta no encontrada")
        }
        return
    }

    val favoriteIds by favoritesViewModel.favoriteIds.observeAsState(emptySet())
    val isFavorite = favoriteIds.contains(card.id)

    val imageUrl = card.cardImages.firstOrNull()?.imageUrlCropped
        ?: card.cardImages.firstOrNull()?.imageUrl
        ?: card.cardImages.firstOrNull()?.imageUrlSmall

    val outer = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .padding(16.dp)

    Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Column(modifier = outer) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = card.name,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(Modifier.height(12.dp))

                    if (imageUrl != null) {
                        GlideImage(
                            model = imageUrl,
                            contentDescription = card.name,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxWidth().height(320.dp)
                        )
                        Spacer(Modifier.height(12.dp))
                    }

                    Text(
                        text = card.humanReadableCardType ?: card.type.orEmpty(),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    if (!card.archetype.isNullOrBlank()) {
                        Spacer(Modifier.height(8.dp))
                        Text("Arquetipo: ${card.archetype}", style = MaterialTheme.typography.bodyMedium)
                    }

                    Spacer(Modifier.height(12.dp))
                    Text(text = card.desc.orEmpty(), style = MaterialTheme.typography.bodyLarge)
                }
            }

            WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
                Row(modifier = outer, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = card.name,
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(Modifier.height(12.dp))

                        if (imageUrl != null) {
                            GlideImage(
                                model = imageUrl,
                                contentDescription = card.name,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxWidth().height(420.dp)
                            )
                        }
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Spacer(Modifier.height(44.dp))
                        Text(
                            text = card.humanReadableCardType ?: card.type.orEmpty(),
                            style = MaterialTheme.typography.bodyMedium
                        )

                        if (!card.archetype.isNullOrBlank()) {
                            Spacer(Modifier.height(8.dp))
                            Text("Arquetipo: ${card.archetype}", style = MaterialTheme.typography.bodyMedium)
                        }

                        Spacer(Modifier.height(12.dp))
                        Text(text = card.desc.orEmpty(), style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }

            else -> {
                Column(modifier = outer) {
                    Text(text = card.name, style = MaterialTheme.typography.headlineSmall)
                }
            }
        }

        // Botón de favorito flotante
        FloatingActionButton(
            onClick = { favoritesViewModel.toggleFavorite(card) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = if (isFavorite) Color.Red else MaterialTheme.colorScheme.primaryContainer
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = if (isFavorite) "Eliminar de favoritos" else "Añadir a favoritos",
                tint = if (isFavorite) Color.White else MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}
