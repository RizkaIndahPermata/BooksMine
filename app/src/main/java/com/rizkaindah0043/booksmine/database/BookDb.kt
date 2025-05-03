package com.rizkaindah0043.booksmine.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rizkaindah0043.booksmine.model.Book

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE book ADD COLUMN isDeleted INTEGER NOT NULL DEFAULT 0")
    }
}

@Database(entities = [Book::class], version = 2, exportSchema = false)
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
                    )
                        .addMigrations(MIGRATION_1_2)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}