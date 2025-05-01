package com.rizkaindah0043.booksmine.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rizkaindah0043.booksmine.model.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookDb : RoomDatabase() {

    abstract val dao: BookDao

    companion object {

        @Volatile
        private var INSTANCE: BookDb? = null

        fun getInstance(context: Context): BookDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookDb::class.java,
                        "book.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}