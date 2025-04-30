package com.rizkaindah0043.booksmine.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
}