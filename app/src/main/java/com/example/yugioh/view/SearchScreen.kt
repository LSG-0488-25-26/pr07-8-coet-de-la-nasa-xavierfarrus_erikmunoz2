package com.example.yugioh.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.yugioh.viewmodel.SearchBarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBarView(myViewModel: SearchBarViewModel) {
    val searchedText by myViewModel.searchedText.observeAsState("")
    val searchHistory by myViewModel.searchHistory.observeAsState(emptyList())

    SearchBar(
        query = searchedText,
        onQueryChange = { myViewModel.onSearchTextChange(it) },
        onSearch = { myViewModel.addToHistory(it) },
        active = false,
        onActiveChange = { },
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search") },
        trailingIcon = {
            if (searchHistory.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear",
                    tint = androidx.compose.ui.graphics.Color.Red,
                    modifier = Modifier.clickable { myViewModel.clearHistory() }
                )
            }
        },
            placeholder = { Text("Buscar en Yugioh App") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
    ) {}
}

@Composable
fun SearchScreen(myViewModel: SearchBarViewModel, paddingValues: androidx.compose.foundation.layout.PaddingValues) {
    val searchHistory by myViewModel.searchHistory.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        MySearchBarView(myViewModel)
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
        ) {
            items(searchHistory) { search ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = search,
                        modifier = Modifier.padding(16.dp),
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
