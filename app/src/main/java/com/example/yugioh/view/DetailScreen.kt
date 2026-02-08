package com.example.yugioh.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.yugioh.model.YugiohCard
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    card: YugiohCard?,
    paddingValues: PaddingValues,
    windowSizeClass: WindowSizeClass
) {
    if (card == null) {
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)) {
            Text("Carta no encontrada")
        }
        return
    }

    val imageUrl = card.cardImages.firstOrNull()?.imageUrlCropped
        ?: card.cardImages.firstOrNull()?.imageUrl
        ?: card.cardImages.firstOrNull()?.imageUrlSmall

    val outer = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .padding(16.dp)

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(modifier = outer) {
                Text(text = card.name, style = MaterialTheme.typography.headlineSmall)
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
                    Text(text = card.name, style = MaterialTheme.typography.headlineSmall)
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
}
