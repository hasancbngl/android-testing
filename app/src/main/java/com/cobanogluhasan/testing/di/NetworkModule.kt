package com.cobanogluhasan.testing.di

import com.cobanogluhasan.testing.data.api.RetrofitAPI
import com.cobanogluhasan.testing.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun injectRetrofit(): RetrofitAPI {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = okhttp3.OkHttpClient.Builder()
            .addInterceptor(logging)
            .callTimeout(6000, TimeUnit.MILLISECONDS)
            .build()
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client)
            .build().create(RetrofitAPI::class.java)
    }

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