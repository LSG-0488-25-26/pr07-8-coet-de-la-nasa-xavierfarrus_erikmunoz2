package com.example.yugioh.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.yugioh.viewmodel.CardListViewModel

@Composable
fun CardDetailScreen(
    navController: NavController,
    cardIndex: String,
    vm: CardListViewModel,
    modifier: Modifier = Modifier
) {
    val cards by vm.cards.observeAsState(emptyList())
    val card = cards.firstOrNull { it.index == cardIndex }

    if (card == null) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Card not found", style = MaterialTheme.typography.bodyLarge)
        }
        return
    }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = card.image),
            contentDescription = card.name,
            modifier = Modifier.fillMaxWidth().height(320.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(card.name, style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))
        Text(card.type, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(8.dp))
        Text("ATK: ${card.attack ?: "-"}   DEF: ${card.defense ?: "-"}")

        Spacer(modifier = Modifier.height(12.dp))
        Text(card.description, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}