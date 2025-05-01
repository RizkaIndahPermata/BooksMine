package com.rizkaindah0043.booksmine.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizkaindah0043.booksmine.database.BookDao
import com.rizkaindah0043.booksmine.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val dao: BookDao) : ViewModel() {

    fun insert(title: String, writer: String, publishDate: String, synopsis: String) {
        val book = Book(
            title = title,
            writer = writer,
            publishDate = publishDate,
            synopsis = synopsis
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(book)
        }
    }
    fun getBook(id: Long): Book? {
        return null
    }
}