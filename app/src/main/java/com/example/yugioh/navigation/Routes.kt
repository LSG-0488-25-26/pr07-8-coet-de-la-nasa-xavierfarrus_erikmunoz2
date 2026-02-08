package com.example.yugioh.navigation

sealed class Routes(val route: String) {
    object ListScreen : Routes("list")
    object SearchScreen : Routes("search")
    object OpenScreen : Routes("open")
    object InventoryScreen : Routes("inventory")

    object DetailScreen : Routes("detail/{cardId}") {
        fun createRoute(cardId: Int) = "detail/$cardId"
    }
}
