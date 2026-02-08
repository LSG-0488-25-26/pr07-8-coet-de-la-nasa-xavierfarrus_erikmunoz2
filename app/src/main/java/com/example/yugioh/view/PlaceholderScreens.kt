package com.example.yugioh.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.foundation.layout.padding

@Composable
fun FavoriteScreen(
    paddingValues: PaddingValues,
    windowSizeClass: WindowSizeClass
) {
    val label = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> "Pantalla Preferits (Compact)"
        WindowWidthSizeClass.Medium -> "Pantalla Preferits (Medium)"
        WindowWidthSizeClass.Expanded -> "Pantalla Preferits (Expanded)"
        else -> "Pantalla Preferits"
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(label)
    }
}
