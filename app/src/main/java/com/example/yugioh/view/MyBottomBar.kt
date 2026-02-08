package com.example.yugioh.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.navigation.NavHostController
import com.example.yugioh.viewmodel.ScaffoldViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.graphics.Color

@Composable
fun MyBottomBar(myViewModel: ScaffoldViewModel, navigationController: NavHostController) {
    val bottomNavigationItems by myViewModel.bottomNavigationItems.observeAsState(emptyList())
    NavigationBar(containerColor = Color.LightGray, contentColor = Color.Black) {
        val navBackEntry by navigationController.currentBackStackEntryAsState()
        val currentRoute = navBackEntry?.destination?.route
        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navigationController.navigate(item.route)
                    }
                }
            )
        }
    }
}
