package com.rizkaindah0043.booksmine.ui.screen

import androidx.lifecycle.ViewModel
import com.rizkaindah0043.booksmine.model.Book

class MainViewModel : ViewModel() {

    val data = listOf(
        Book(
            1,
            "The Midnight Library",
            "Matt Haig",
            "2020-09-29",
            "A story about a library between life and death where each book contains a different life you could have lived."
        ),
        Book(
            2,
            "Laskar Pelangi",
            "Andrea Hirata",
            "2005-09-01",
             "Kisah inspiratif sekelompok anak miskin di Belitung yang berjuang demi pendidikan dan mimpi."
        ),
        Book(
            3,
            "Atomic Habits",
            "James Clear",
            "2018-10-16",
            "A practical guide to building good habits and breaking bad ones through small, incremental changes."
        ),
        Book(
            4,
            "Bumi",
            "Tere Liye",
            "2014-01-01",
            "Petualangan remaja bernama Raib yang menemukan dunia paralel dengan kekuatan misterius."
        ),
        Book(
            5,
            "To Kill a Mockingbird",
            "Harper Lee",
            "1960-07-11",
            "A powerful novel about racial injustice and moral growth in the American South."
        ),
        Book(
            6,
            "To Kill",
            "Harper",
            "1969-07-11",
            "A powerful novel about racial injustice and moral growth in the American South."
        ),
        Book(
            7,
            "To Kill a Mockingbird",
            "Harper Lee",
            "1960-07-11",
            "A powerful novel about racial injustice and moral growth in the American South."
        )
    )
}