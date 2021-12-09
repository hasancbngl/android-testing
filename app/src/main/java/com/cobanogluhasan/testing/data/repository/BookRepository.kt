package com.cobanogluhasan.testing.data.repository

import androidx.lifecycle.LiveData
import com.cobanogluhasan.testing.data.api.RetrofitAPI
import com.cobanogluhasan.testing.data.db.dao.BookDao
import com.cobanogluhasan.testing.data.db.entity.Book
import com.cobanogluhasan.testing.data.model.ImagesResponse
import com.cobanogluhasan.testing.util.Constants.API_KEY
import com.cobanogluhasan.testing.util.Resource
import java.lang.Exception
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookDao: BookDao,
    private val retrofitAPI: RetrofitAPI
) : BookRepositoryInterface {
    override suspend fun insertBook(book: Book) {
        bookDao.insertBook(book)
    }

    override suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }

    override fun getBooks(): LiveData<List<Book>> {
        return bookDao.observeBooks()
    }

    override suspend fun searchImage(imageString: String): Resource<ImagesResponse> {
        return try {
            val response = retrofitAPI.imageSearch(imageString)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else {
                Resource.error("Error",null)
            }
        } catch (e: Exception) {
            Resource.error("${e.message} , ${e.printStackTrace()}",null)
        }
    }
}