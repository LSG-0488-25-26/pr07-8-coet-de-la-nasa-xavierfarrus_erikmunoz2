package com.example.yugioh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.yugioh.ui.theme.YugiohTheme
import com.example.yugioh.view.MyAppNavHost
import com.example.yugioh.view.MyBottomBar
import com.example.yugioh.view.MyTopAppBar
import com.example.yugioh.viewmodel.CardsViewModel
import com.example.yugioh.viewmodel.InventoryViewModel
import com.example.yugioh.viewmodel.ScaffoldViewModel
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            YugiohTheme {
                val navController = rememberNavController()

                val cardsVm: CardsViewModel = viewModel()
                val scaffoldVm: ScaffoldViewModel = viewModel()
                val inventoryVm: InventoryViewModel = viewModel()

                val loading by cardsVm.loading.observeAsState(false)
                val error by cardsVm.error.observeAsState(null)

                // ✅ Window size class (Compact / Medium / Expanded)
                val windowSizeClass = calculateWindowSizeClass(this)

                // ✅ Cargar cartas al iniciar
                LaunchedEffect(Unit) {
                    cardsVm.loadCards()
                }

                // ✅ Timeout que se cancela si loading pasa a false
                var timedOut by remember { mutableStateOf(false) }
                LaunchedEffect(loading) {
                    if (loading) {
                        kotlinx.coroutines.delay(15000)
                        timedOut = true
                    } else {
                        timedOut = false
                    }
                }

                when {
                    loading && !timedOut -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }

                    loading && timedOut -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.align(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "La carga está tardando demasiado.",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Button(
                                    onClick = {
                                        timedOut = false
                                        cardsVm.loadCards()
                                    },
                                    modifier = Modifier.padding(top = 12.dp)
                                ) {
                                    Text("Reintentar")
                                }
                            }
                        }
                    }

                    error != null -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.align(Alignment.Center),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Error: $error",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Button(
                                    onClick = {
                                        timedOut = false
                                        cardsVm.loadCards()
                                    },
                                    modifier = Modifier.padding(top = 12.dp)
                                ) {
                                    Text("Reintentar")
                                }
                            }
                        }
                    }

                    else -> {
                        Scaffold(
                            topBar = { MyTopAppBar(navController) },
                            bottomBar = { MyBottomBar(scaffoldVm, navController) }
                        ) { paddingValues ->
                            MyAppNavHost(
                                navController = navController,
                                myViewModel = scaffoldVm,
                                paddingValues = paddingValues,
                                cardsViewModel = cardsVm,
                                inventoryViewModel = inventoryVm,
                                windowSizeClass = windowSizeClass
                            )
                        }
                    }
                }
            }
        }
    }
}
