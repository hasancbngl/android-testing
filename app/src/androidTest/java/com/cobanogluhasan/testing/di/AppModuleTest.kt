package com.cobanogluhasan.testing.di

import android.content.Context
import androidx.room.Room
import com.cobanogluhasan.testing.data.db.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModuleTest {

    @Provides
    @Named("testDb")
    fun injectTestDb(@ApplicationContext context: Context) = Room.inMemoryDatabaseBuilder(
        context, BookDatabase::class.java
    ).allowMainThreadQueries().build()
}