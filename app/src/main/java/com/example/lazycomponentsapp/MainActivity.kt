package com.example.lazycomponentsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lazycomponentsapp.navigation.Routes
import com.example.lazycomponentsapp.ui.theme.LazyComponentsAppTheme
import com.example.lazycomponentsapp.view.CardDetailScreen
import com.example.lazycomponentsapp.view.CardListScreen
import com.example.lazycomponentsapp.viewmodel.CardListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LazyComponentsAppTheme {
                val navController = rememberNavController()
                val cardVm: CardListViewModel = viewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.ListScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable(Routes.ListScreen.route) {
                            CardListScreen(navController = navController, vm = cardVm)
                        }

                        composable(
                            route = Routes.DetailScreen.route,
                            arguments = listOf(navArgument("cardIndex") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val cardIndex = backStackEntry.arguments?.getString("cardIndex").orEmpty()
                            CardDetailScreen(navController = navController, cardIndex = cardIndex, vm = cardVm)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    LazyComponentsAppTheme { Text("Preview") }
}