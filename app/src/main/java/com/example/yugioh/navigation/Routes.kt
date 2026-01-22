package com.example.yugioh.navigation

sealed class Routes(val route: String) {

    object ListScreen : Routes("list")

    object DetailScreen : Routes("detail/{cardIndex}") {
        fun createRoute(cardIndex: String) = "detail/$cardIndex"
    }
}