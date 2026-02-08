package com.example.yugioh.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.yugioh.navigation.Routes

sealed class BottomNavigationScreens(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavigationScreens(Routes.ListScreen.route, Icons.Filled.Home, "Inicio")
    object Favorite : BottomNavigationScreens("favorite", Icons.Filled.Favorite, "Preferidos")

    object Inventory : BottomNavigationScreens("inventory", Icons.Filled.Menu, "Inventario")

    object Open : BottomNavigationScreens(Routes.OpenScreen.route, Icons.Filled.Star, "Apertura")

}
