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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun MyAppNavHost(
    navController: NavHostController,
    myViewModel: ScaffoldViewModel,
    paddingValues: PaddingValues,
    cardsViewModel: CardsViewModel
) {
    NavHost(navController = navController, startDestination = Routes.ListScreen.route) {
        composable(Routes.ListScreen.route) {
            HomeScreen(navController, paddingValues, cardsViewModel)
        }
        composable("favorite") { FavoriteScreen() }
        composable("search") {
            val searchVm: SearchBarViewModel = viewModel()
            SearchScreen(searchVm, paddingValues)
        }
        composable(Routes.DetailScreen.route) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getString("cardId")?.toIntOrNull()
            val card = cardId?.let { cardsViewModel.getCardFromLoadedList(it) }
            DetailScreen(navController = navController, card = card)
        }
    }
}
