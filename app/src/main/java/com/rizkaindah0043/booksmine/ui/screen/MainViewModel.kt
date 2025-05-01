package com.rizkaindah0043.booksmine.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizkaindah0043.booksmine.database.BookDao
import com.rizkaindah0043.booksmine.model.Book
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: BookDao) : ViewModel() {

    val data: StateFlow<List<Book>> = dao.getBook().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )
    fun getBook(id: Long): Book? {
        return data.value.find { it.id == id }
    }
}