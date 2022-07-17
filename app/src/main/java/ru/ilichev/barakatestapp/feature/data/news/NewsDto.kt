package ru.ilichev.barakatestapp.feature.data.news

class NewsWrapper(
    val articles: List<NewsDto>
)

class NewsDto(
    val title: String,
    val publishedAt: String,
    val urlToImage: String,
    val content: String
)