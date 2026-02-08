package com.example.yugioh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.yugioh.view.SearchScreen
import com.example.yugioh.view.MyTopAppBar
import com.example.yugioh.view.MyBottomBar
import com.example.yugioh.view.MyAppNavHost
import com.example.yugioh.viewmodel.CardsViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            YugiohTheme {
                val navController = rememberNavController()
                val vm: CardsViewModel = viewModel()
                val scaffoldVm: com.example.yugioh.viewmodel.ScaffoldViewModel = viewModel()

                val cards by vm.cards.observeAsState(emptyList())
                val loading by vm.loading.observeAsState(false)
                val error by vm.error.observeAsState(null)

                LaunchedEffect(Unit) {
                    vm.loadCards()
                }

                var timedOut by remember { mutableStateOf(false) }

                if (loading && !timedOut) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    LaunchedEffect(Unit) {
                        kotlinx.coroutines.delay(15000)
                        if (loading) timedOut = true
                    }
                } else if (loading && timedOut) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        androidx.compose.foundation.layout.Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "La carga está tardando demasiado.",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Button(
                                onClick = { vm.loadCards(); timedOut = false },
                                modifier = Modifier.padding(top = 12.dp)
                            ) {
                                Text("Reintentar")
                            }
                        }
                    }
                } else if (error != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
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
                } else {
                    androidx.compose.material3.Scaffold(
                        topBar = { MyTopAppBar(navController) },
                        bottomBar = { MyBottomBar(scaffoldVm, navController) }
                    ) { paddingValues ->
                        MyAppNavHost(navController = navController, myViewModel = scaffoldVm, paddingValues = paddingValues, cardsViewModel = vm)
                    }
                }
            }
        }
    }
}
