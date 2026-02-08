package com.example.yugioh.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yugioh.viewmodel.CardsViewModel
import com.example.yugioh.viewmodel.SearchBarViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(paddingValues: PaddingValues) {
    val vm: CardsViewModel = viewModel()
    val searchVm: SearchBarViewModel = viewModel()

    val cards by vm.cards.observeAsState(emptyList())
    val loading by vm.loading.observeAsState(false)

    LaunchedEffect(Unit) { vm.loadCards() }

    // Forward searched text to CardsViewModel to filter
    val searchedText by searchVm.searchedText.observeAsState("")
    LaunchedEffect(searchedText) { vm.setQuery(searchedText) }

    val filtered by vm.filtered.observeAsState(cards)

    Column(modifier = Modifier.fillMaxWidth().padding(paddingValues)) {
        MySearchBarView(searchVm)
        if (loading) {
            Text("Cargando...")
        } else {
            ListScreen(cards = filtered, onOpenDetail = { /* navigation handled by NavHost */ })
        }
    }
}
