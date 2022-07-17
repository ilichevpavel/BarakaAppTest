package ru.ilichev.barakatestapp.feature.data.news

import javax.inject.Inject

class NewsInMemoryDataSource @Inject constructor() {

    var news: List<NewsDto> = emptyList()
        private set

    fun update(news: List<NewsDto>) {
        this.news = news
    }
}