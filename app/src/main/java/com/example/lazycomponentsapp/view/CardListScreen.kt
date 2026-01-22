package com.example.lazycomponentsapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import com.example.lazycomponentsapp.model.YuGiOhCard
import com.example.lazycomponentsapp.navigation.Routes
import com.example.lazycomponentsapp.viewmodel.CardListViewModel

@Composable
fun CardListScreen(
    navController: NavController,
    vm: CardListViewModel,
    modifier: Modifier = Modifier
) {
    val cards by vm.cards.observeAsState(emptyList())

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cards) { card ->
            CardItem(
                card = card,
                onClick = { navController.navigate(Routes.DetailScreen.createRoute(card.index)) }
            )
        }
    }
}

@Composable
private fun CardItem(card: YuGiOhCard, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = card.image),
                contentDescription = card.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = card.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = card.type, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}