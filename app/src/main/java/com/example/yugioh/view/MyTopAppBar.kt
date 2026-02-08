package com.example.yugioh.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavHostController) {
    TopAppBar(
        title = { Text(text = "Yugioh") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.LightGray,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),
        actions = {
            // No actions: search bar is visible in the main content (HomeScreen)
        }
    )
}
