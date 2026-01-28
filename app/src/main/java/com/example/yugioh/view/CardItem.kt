package com.example.yugioh.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.yugioh.model.YugiohCard

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CardItem(
    card: YugiohCard,
    onItemSelected: (Int) -> Unit
) {
    Card(
        border = BorderStroke(1.dp, Color.LightGray),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .fillMaxWidth()
            .clickable { onItemSelected(card.id) }
    ) {
        Row(modifier = Modifier.padding(12.dp)) {

            val imageUrl = card.cardImages.firstOrNull()?.imageUrlSmall
                ?: card.cardImages.firstOrNull()?.imageUrl

            if (imageUrl != null) {
                GlideImage(
                    model = imageUrl,
                    contentDescription = card.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(90.dp)
                )
                Spacer(Modifier.width(12.dp))
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = card.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = card.humanReadableCardType ?: card.type.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium
                )
                if (!card.race.isNullOrBlank()) {
                    Text(
                        text = card.race,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
