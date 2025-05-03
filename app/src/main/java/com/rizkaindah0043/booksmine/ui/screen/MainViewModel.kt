package com.rizkaindah0043.booksmine.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizkaindah0043.booksmine.database.BookDao
import com.rizkaindah0043.booksmine.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val dao: BookDao) : ViewModel() {

    val data: StateFlow<List<Book>> = dao.getBook().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )
    fun undoDelete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.undoDeleteById(id)
        }
    }
    fun deletePermanent(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
    val deletedData: StateFlow<List<Book>> = dao.getDeletedBook().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )
}