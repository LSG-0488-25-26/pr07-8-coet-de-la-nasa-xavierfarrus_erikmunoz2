// view/SearchScreen.kt  (incluye MySearchBarView)
package com.example.yugioh.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yugioh.viewmodel.SearchBarViewModel
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

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
fun SearchScreen(
    myViewModel: SearchBarViewModel,
    paddingValues: PaddingValues,
    windowSizeClass: WindowSizeClass
) {
    val searchHistory by myViewModel.searchHistory.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        MySearchBarView(myViewModel)
        Spacer(modifier = Modifier.height(16.dp))

        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) {
                    items(searchHistory) { search ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Text(
                                text = search,
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            WindowWidthSizeClass.Medium -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(searchHistory) { search ->
                        Card(
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Text(
                                text = search,
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            WindowWidthSizeClass.Expanded -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(searchHistory) { search ->
                        Card(
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Text(
                                text = search,
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            else -> Unit
        }
    }
}
