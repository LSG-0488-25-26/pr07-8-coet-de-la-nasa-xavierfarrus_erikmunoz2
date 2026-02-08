package com.example.yugioh.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yugioh.view.BottomNavigationScreens

class ScaffoldViewModel : ViewModel() {
    private val _bottomNavigationItems = MutableLiveData<List<BottomNavigationScreens>>(emptyList())
    val bottomNavigationItems: LiveData<List<BottomNavigationScreens>> = _bottomNavigationItems

    init {
        _bottomNavigationItems.value = listOf(
            BottomNavigationScreens.Home,
            BottomNavigationScreens.Favorite,
            BottomNavigationScreens.Open,
            BottomNavigationScreens.Inventory

        )
    }
}
