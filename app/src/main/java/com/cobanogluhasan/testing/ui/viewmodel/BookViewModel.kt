package com.cobanogluhasan.testing.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cobanogluhasan.testing.data.db.entity.Book
import com.cobanogluhasan.testing.data.model.ImagesResponse
import com.cobanogluhasan.testing.data.repository.BookRepositoryInterface
import com.cobanogluhasan.testing.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class BookViewModel @Inject constructor(
    private val repository: BookRepositoryInterface
) : ViewModel() {

    val bookList = repository.getBooks()

    private val images = MutableLiveData<Resource<ImagesResponse>>()
    val imageList: LiveData<Resource<ImagesResponse>>
        get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl: LiveData<String>
        get() = selectedImage

    private var insertMessage = MutableLiveData<Resource<Book>>()
    val insertBookmsg: LiveData<Resource<Book>>
        get() = insertMessage

    //if it don't get reseted will always hold the first value
    /* fun resetInsertMsg() {
         insertMessage = MutableLiveData<Resource<Book>>()
     } */

    fun setSelectedImageUrl(url: String) {
        selectedImage.postValue(url)
    }

    fun insertBook(book: Book) = viewModelScope.launch {
        repository.insertBook(book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch {
        repository.deleteBook(book)
    }

    fun makeBook(author: String, bookName: String, year: String) {
        if (author.isEmpty() || bookName.isEmpty() || year.isEmpty()) {
            insertMessage.postValue(Resource.error("Enter author, name and year", null))
            return
        }

        val yearInt = try {
            year.toInt()
        } catch (e: Exception) {
            insertMessage.postValue(Resource.error("Year should be number", null))
            return
        }

        val book = Book(bookName, author, yearInt, selectedImage.value ?: "")
        insertBook(book)
        setSelectedImageUrl("")
        insertMessage.postValue(Resource.success(book))
    }

    fun searchImage(text: String) {
        if (text.isEmpty()) return
        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(text)
            images.value = response
        }
    }
}