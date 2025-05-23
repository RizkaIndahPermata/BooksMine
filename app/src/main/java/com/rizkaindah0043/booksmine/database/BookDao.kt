package com.rizkaindah0043.booksmine.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rizkaindah0043.booksmine.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert
    suspend fun insert(book: Book)

    @Update
    suspend fun update(book: Book)

    @Query("SELECT * FROM book WHERE isDeleted = 0 ORDER BY title ASC")
    fun getBook(): Flow<List<Book>>

    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun getBookById(id: Long): Book?

    @Query("DELETE  FROM book WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE book SET isDeleted = 1 WHERE id = :id")
    suspend fun softDeleteById(id: Long)

    @Query("UPDATE book SET isDeleted = 0 WHERE id = :id")
    suspend fun undoDeleteById(id: Long)

    @Query("SELECT * FROM book WHERE isDeleted = 1 ORDER BY title ASC")
    fun getDeletedBook(): Flow<List<Book>>
}