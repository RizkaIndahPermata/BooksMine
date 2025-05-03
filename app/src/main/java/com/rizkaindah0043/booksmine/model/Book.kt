package com.rizkaindah0043.booksmine.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val writer: String,
    val publishDate: String,
    val synopsis: String,
    val isDeleted: Boolean = false
)
