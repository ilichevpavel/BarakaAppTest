package ru.ilichev.barakatestapp.feature.data.news

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import ru.ilichev.barakatestapp.feature.domain.model.News
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val localDataSource: NewsLocalDataSource,
    private val inMemoryDataSource: NewsInMemoryDataSource
) {

    fun getNews(): Flow<List<News>> {
        return flow {
            val news = inMemoryDataSource.news
                .takeIf { it.isNotEmpty() }
                ?: localDataSource.getNews().also(inMemoryDataSource::update)

            emit(news)
        }
            .map { it.map(::toModel) }
            .flowOn(Dispatchers.IO)
    }

    private fun toModel(model: NewsDto): News = with(model) {
        return News(
            title,
            publishedAt,
            urlToImage,
            content
        )
    }
}