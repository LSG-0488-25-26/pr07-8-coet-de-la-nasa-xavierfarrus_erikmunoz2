package com.example.yugioh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yugioh.navigation.Routes
import com.example.yugioh.ui.theme.YugiohTheme
import com.example.yugioh.view.DetailScreen
import com.example.yugioh.view.ListScreen
import com.example.yugioh.viewmodel.CardsViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            YugiohTheme {
                val navController = rememberNavController()
                val vm: CardsViewModel = viewModel()

                val cards by vm.cards.observeAsState(emptyList())
                val loading by vm.loading.observeAsState(false)
                val error by vm.error.observeAsState(null)

                LaunchedEffect(Unit) {
                    vm.loadCards()
                }

                when {
                    loading -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }

                    error != null -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            // Mensaje + botón retry centrados
                            androidx.compose.foundation.layout.Column(
                                modifier = Modifier.align(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Error: $error",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Button(
                                    onClick = { vm.loadCards() },
                                    modifier = Modifier.padding(top = 12.dp)
                                ) {
                                    Text("Reintentar")
                                }
                            }
                        }
                    }

                    else -> {
                        NavHost(
                            navController = navController,
                            startDestination = Routes.ListScreen.route
                        ) {
                            composable(Routes.ListScreen.route) {
                                ListScreen(
                                    cards = cards,
                                    onOpenDetail = { cardId ->
                                        navController.navigate(Routes.DetailScreen.createRoute(cardId))
                                    }
                                )
                            }

                            composable(
                                route = Routes.DetailScreen.route,
                                arguments = listOf(navArgument("cardId") { type = NavType.IntType })
                            ) { backStackEntry ->
                                val cardId = backStackEntry.arguments?.getInt("cardId") ?: -1
                                val card = vm.getCardFromLoadedList(cardId)
                                DetailScreen(navController = navController, card = card)

                            }
                        }
                    }
                }
            }
        }
    }
}
