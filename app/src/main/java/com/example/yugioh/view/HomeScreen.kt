// view/HomeScreen.kt
package com.example.yugioh.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.yugioh.navigation.Routes
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yugioh.viewmodel.CardsViewModel
import com.example.yugioh.viewmodel.SearchBarViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

@Composable
fun HomeScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    cardsViewModel: CardsViewModel,
    windowSizeClass: WindowSizeClass
) {
    val vm: CardsViewModel = cardsViewModel
    val searchVm: SearchBarViewModel = viewModel()

    val cards by vm.cards.observeAsState(emptyList())
    val loading by vm.loading.observeAsState(false)

    val searchedText by searchVm.searchedText.observeAsState("")
    LaunchedEffect(searchedText) { vm.setQuery(searchedText) }

    val filtered by vm.filtered.observeAsState(cards)

    Column(modifier = Modifier.fillMaxWidth().padding(paddingValues)) {
        MySearchBarView(searchVm)

        if (loading) {
            Text("Cargando...")
        } else {
            when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    ListScreen(
                        cards = filtered,
                        onOpenDetail = { cardId ->
                            navController.navigate(Routes.DetailScreen.createRoute(cardId)) {
                                launchSingleTop = true
                            }
                        }
                    )
                }

                WindowWidthSizeClass.Medium -> {
                    CardsGridScreen(
                        cards = filtered,
                        columns = 2,
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                        onOpenDetail = { cardId ->
                            navController.navigate(Routes.DetailScreen.createRoute(cardId)) {
                                launchSingleTop = true
                            }
                        }
                    )
                }

                WindowWidthSizeClass.Expanded -> {
                    CardsGridScreen(
                        cards = filtered,
                        columns = 3,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                        onOpenDetail = { cardId ->
                            navController.navigate(Routes.DetailScreen.createRoute(cardId)) {
                                launchSingleTop = true
                            }
                        }
                    )
                }

                else -> {
                    ListScreen(
                        cards = filtered,
                        onOpenDetail = { cardId ->
                            navController.navigate(Routes.DetailScreen.createRoute(cardId))
                        }
                    )
                }
            }
        }
    }
}
