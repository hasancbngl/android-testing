package com.cobanogluhasan.testing.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cobanogluhasan.testing.data.db.entity.Book
import com.cobanogluhasan.testing.data.model.ImagesResponse
import com.cobanogluhasan.testing.data.repository.BookRepositoryInterface
import com.cobanogluhasan.testing.util.Resource

class FakeBookRepositoryAndroid : BookRepositoryInterface {

    private val books = mutableListOf<Book>()
    private val booksLiveData = MutableLiveData<List<Book>>(books)

    override suspend fun insertBook(book: Book) {
        books.add(book)
        refreshLiveData()
    }

    override suspend fun deleteBook(book: Book) {
        books.remove(book)
        refreshLiveData()
    }

    override fun getBooks(): LiveData<List<Book>> {
        return booksLiveData
    }

    override suspend fun searchImage(imageString: String): Resource<ImagesResponse> =
        Resource.success(ImagesResponse(emptyList(), 0, 0))

    private fun refreshLiveData() {
        booksLiveData.postValue(books)
    }
}