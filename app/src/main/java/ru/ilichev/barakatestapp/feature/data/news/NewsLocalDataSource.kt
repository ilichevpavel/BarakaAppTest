package ru.ilichev.barakatestapp.feature.data.news

import android.content.Context
import androidx.annotation.WorkerThread
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.ilichev.barakatestapp.common.getJsonDataFromAsset
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val gson: Gson
) {

    @WorkerThread
    fun getNews(): List<NewsDto> {
        val rawResponse = getJsonDataFromAsset(context, "news.json")
        return gson.fromJson(rawResponse, NewsWrapper::class.java).articles
    }
}