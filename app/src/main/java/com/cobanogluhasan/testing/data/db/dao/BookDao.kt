package com.cobanogluhasan.testing.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cobanogluhasan.testing.data.db.entity.Book

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT * FROM books")
    fun observeBooks(): LiveData<List<Book>>
}