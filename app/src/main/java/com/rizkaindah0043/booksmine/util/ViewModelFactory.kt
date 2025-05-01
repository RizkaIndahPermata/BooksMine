package com.rizkaindah0043.booksmine.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rizkaindah0043.booksmine.database.BookDb
import com.rizkaindah0043.booksmine.ui.screen.DetailViewModel
import com.rizkaindah0043.booksmine.ui.screen.MainViewModel

class ViewModelFactory(
    private val context: Context
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = BookDb.getInstance(context).dao
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}