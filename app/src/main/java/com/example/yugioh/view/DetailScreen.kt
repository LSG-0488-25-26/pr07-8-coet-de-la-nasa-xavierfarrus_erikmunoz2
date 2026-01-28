package com.example.yugioh.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    card: YugiohCard?
) {
    if (card == null) {
        Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Carta no encontrada")
        }
        return
    }

    val imageUrl = card.cardImages.firstOrNull()?.imageUrlCropped
        ?: card.cardImages.firstOrNull()?.imageUrl
        ?: card.cardImages.firstOrNull()?.imageUrlSmall

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }

        Spacer(Modifier.height(12.dp))

        Text(
            text = card.name,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(12.dp))

        if (imageUrl != null) {
            GlideImage(
                model = imageUrl,
                contentDescription = card.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
            )
            Spacer(Modifier.height(12.dp))
        }

        Text(
            text = card.humanReadableCardType ?: card.type.orEmpty(),
            style = MaterialTheme.typography.bodyMedium
        )

        if (!card.archetype.isNullOrBlank()) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Arquetipo: ${card.archetype}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(Modifier.height(12.dp))

        Text(
            text = card.desc.orEmpty(),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
