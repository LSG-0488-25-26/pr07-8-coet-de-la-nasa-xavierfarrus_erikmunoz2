// view/InventoryScreen.kt
package com.example.yugioh.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yugioh.viewmodel.InventoryViewModel
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

@Composable
fun InventoryScreen(
    inventoryVm: InventoryViewModel,
    paddingValues: PaddingValues,
    windowSizeClass: WindowSizeClass,
    favoriteIds: Set<Int> = emptySet(),
    onOpenDetail: (Int) -> Unit
) {
    val inventory by inventoryVm.inventory.observeAsState(emptyList())
    val count = inventory.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Inventario", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.width(10.dp))
            Text("($count)", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(Modifier.height(8.dp))

        if (inventory.isEmpty()) {
            Text("No tienes cartas guardadas todavía.")
            return
        }

        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(inventory) { card ->
                        CardItem(
                            card = card,
                            onItemSelected = onOpenDetail,
                            isFavorite = favoriteIds.contains(card.id)
                        )
                    }
                }
            }

            WindowWidthSizeClass.Medium -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(inventory) { card ->
                        CardItem(
                            card = card,
                            onItemSelected = onOpenDetail,
                            isFavorite = favoriteIds.contains(card.id)
                        )
                    }
                }
            }

            WindowWidthSizeClass.Expanded -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(inventory) { card ->
                        CardItem(
                            card = card,
                            onItemSelected = onOpenDetail,
                            isFavorite = favoriteIds.contains(card.id)
                        )
                    }
                }
            }

            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(inventory) { card ->
                        CardItem(
                            card = card,
                            onItemSelected = onOpenDetail,
                            isFavorite = favoriteIds.contains(card.id)
                        )
                    }
                }
            }
        }
    }
}
