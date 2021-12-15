package com.cobanogluhasan.testing.di

import android.content.Context
import androidx.room.Room
import com.cobanogluhasan.testing.data.db.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Singleton
    @Provides
    fun injectRoomDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BookDatabase::class.java, "BookDB").build()

    @Singleton
    @Provides
    fun injectDao(db: BookDatabase) = db.bookDao()
}