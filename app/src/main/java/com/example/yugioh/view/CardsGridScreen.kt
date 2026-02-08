package com.example.yugioh.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yugioh.model.YugiohCard

@Composable
fun CardsGridScreen(
    cards: List<YugiohCard>,
    columns: Int,
    contentPadding: PaddingValues,
    onOpenDetail: (Int) -> Unit,
    favoriteIds: Set<Int> = emptySet()
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.fillMaxSize(),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cards) { card ->
            CardItem(
                card = card,
                onItemSelected = onOpenDetail,
                isFavorite = favoriteIds.contains(card.id)
            )
        }
    }
}
