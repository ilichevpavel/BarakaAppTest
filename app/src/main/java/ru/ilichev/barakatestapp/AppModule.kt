package ru.ilichev.barakatestapp

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.ilichev.barakatestapp.common.CsvParser

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideCsvParser(@ApplicationContext context: Context) = CsvParser(context)
}