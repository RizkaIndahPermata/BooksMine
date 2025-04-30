package com.rizkaindah0043.booksmine.navigation

import com.rizkaindah0043.booksmine.ui.screen.KEY_ID_BOOK

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_BOOK}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}