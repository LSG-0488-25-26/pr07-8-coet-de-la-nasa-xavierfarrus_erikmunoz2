// view/OpenScreen.kt
package com.example.yugioh.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yugioh.model.YugiohCard
import com.example.yugioh.viewmodel.InventoryViewModel
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

@Composable
fun OpenScreen(
    allCards: List<YugiohCard>,
    inventoryVm: InventoryViewModel,
    paddingValues: PaddingValues,
    windowSizeClass: WindowSizeClass,
    onOpenDetail: (Int) -> Unit
) {
    val openedPack by inventoryVm.openedPack.observeAsState(emptyList())

    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage by inventoryVm.snackbarMessage.observeAsState(null)

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            inventoryVm.clearSnackbarMessage()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text("Apertura (5 cartas)", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { inventoryVm.openPack5(allCards) },
                    enabled = allCards.isNotEmpty()
                ) { Text("Abrir pack x5") }

                Button(
                    onClick = { inventoryVm.addPackToInventory() },
                    enabled = openedPack.isNotEmpty()
                ) { Text("Guardar pack") }
            }

            Spacer(Modifier.height(12.dp))

            if (allCards.isEmpty()) {
                Text("Cargando cartas...")
                return@Column
            }

            if (openedPack.isEmpty()) {
                Text("Pulsa “Abrir pack x5” para sacar 5 cartas.")
                return@Column
            }

            when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        contentPadding = PaddingValues(bottom = 60.dp)
                    ) {
                        items(openedPack) { card ->
                            CardItem(card = card, onItemSelected = onOpenDetail)
                        }
                    }
                }

                WindowWidthSizeClass.Medium -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 60.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(openedPack) { card ->
                            CardItem(card = card, onItemSelected = onOpenDetail)
                        }
                    }
                }

                WindowWidthSizeClass.Expanded -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 60.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(openedPack) { card ->
                            CardItem(card = card, onItemSelected = onOpenDetail)
                        }
                    }
                }

                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(openedPack) { card ->
                            CardItem(card = card, onItemSelected = onOpenDetail)
                        }
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(12.dp)
        )
    }
}
