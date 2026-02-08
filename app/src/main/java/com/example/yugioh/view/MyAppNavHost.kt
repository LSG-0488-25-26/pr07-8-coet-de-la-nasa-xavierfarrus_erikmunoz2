package com.example.yugioh.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.yugioh.navigation.Routes
import com.example.yugioh.viewmodel.ScaffoldViewModel
import com.example.yugioh.viewmodel.CardsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yugioh.viewmodel.SearchBarViewModel
import com.example.yugioh.viewmodel.InventoryViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.material3.windowsizeclass.WindowSizeClass


@Composable
fun MyAppNavHost(
    navController: NavHostController,
    myViewModel: ScaffoldViewModel,
    paddingValues: PaddingValues,
    cardsViewModel: CardsViewModel,
    inventoryViewModel: InventoryViewModel,
    windowSizeClass: WindowSizeClass
) {
    val cards by cardsViewModel.cards.observeAsState(emptyList())

    NavHost(
        navController = navController,
        startDestination = Routes.ListScreen.route
    ) {
        composable(Routes.ListScreen.route) {
            HomeScreen(
                navController = navController,
                paddingValues = paddingValues,
                cardsViewModel = cardsViewModel,
                windowSizeClass = windowSizeClass
            )
        }

        composable("favorite") {
            FavoriteScreen(paddingValues = paddingValues, windowSizeClass = windowSizeClass)
        }

        composable("search") {
            val searchVm: SearchBarViewModel = viewModel()
            SearchScreen(
                myViewModel = searchVm,
                paddingValues = paddingValues,
                windowSizeClass = windowSizeClass
            )
        }

        // Apertura
        composable(Routes.OpenScreen.route) {
            OpenScreen(
                allCards = cards,
                inventoryVm = inventoryViewModel,
                paddingValues = paddingValues,
                windowSizeClass = windowSizeClass,
                onOpenDetail = { cardId ->
                    navController.navigate(Routes.DetailScreen.createRoute(cardId)) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // Inventario
        composable(Routes.InventoryScreen.route) {
            InventoryScreen(
                inventoryVm = inventoryViewModel,
                paddingValues = paddingValues,
                windowSizeClass = windowSizeClass,
                onOpenDetail = { cardId ->
                    navController.navigate(Routes.DetailScreen.createRoute(cardId)) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // Detail
        composable(Routes.DetailScreen.route) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getString("cardId")?.toIntOrNull()

            val cardFromApi = cardId?.let { cardsViewModel.getCardFromLoadedList(it) }
            val cardFromInventory = inventoryViewModel.inventory.value
                ?.firstOrNull { it.id == cardId }

            DetailScreen(
                navController = navController,
                card = cardFromApi ?: cardFromInventory,
                paddingValues = paddingValues,
                windowSizeClass = windowSizeClass
            )
        }
    }
}
