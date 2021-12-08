package com.cobanogluhasan.testing.data.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.data.api.RetrofitAPI
import com.cobanogluhasan.testing.data.db.BookDatabase
import com.cobanogluhasan.testing.data.db.dao.BookDao
import com.cobanogluhasan.testing.data.repository.BookRepository
import com.cobanogluhasan.testing.data.repository.BookRepositoryInterface
import com.cobanogluhasan.testing.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BookDatabase::class.java, "BookDB").build()

    @Singleton
    @Provides
    fun injectDao(db: BookDatabase) = db.bookDao()

    @Singleton
    @Provides
    fun injectRetrofit(): RetrofitAPI {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectRepo(dao: BookDao, api: RetrofitAPI)  = BookRepository(dao, api) as BookRepositoryInterface

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) =
        Glide.with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )


    /*
    **Retrofit example with dynamic url and custom api class
    @Singleton
    @Provides
    fun injectRetrofit(s: String, c: Any): Any? {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(c as Class<*>)
    }

    * injectRetrofit("http", RetrofitAPI::class.java)
     */
}