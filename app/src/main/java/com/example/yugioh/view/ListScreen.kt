package com.example.yugioh.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yugioh.model.YugiohCard

@Composable
fun ListScreen(
    cards: List<YugiohCard>,
    onOpenDetail: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(cards) { card ->
            CardItem(card = card, onItemSelected = onOpenDetail)
        }
    }
}
