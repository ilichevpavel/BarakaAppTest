package ru.ilichev.barakatestapp.feature.presentation

import ru.ilichev.barakatestapp.feature.domain.model.News
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.ShortNewsSection.ShortNewsItem
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.ShortNewsSection
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.FullNewsItem
import javax.inject.Inject

private const val NEWS_PREVIEW_COUNT = 6

class NewsViewItemConverter @Inject constructor() : (List<News>) -> List<ItemViewTyped> {

    override fun invoke(news: List<News>): List<ItemViewTyped> {
        if (news.isEmpty()) return emptyList()

        val newsWrappers = mutableListOf<ItemViewTyped>()

        val previewNews = news.take(NEWS_PREVIEW_COUNT).map(::mapToNewsPreview)
        newsWrappers.add(ShortNewsSection(previewNews))

        val remainingItemsCount = news.count() - previewNews.count()

        if (remainingItemsCount > 0) {
            val fullNews = news.takeLast(remainingItemsCount).map(::mapToNewsFull)
            newsWrappers.addAll(fullNews)
        }

        return newsWrappers
    }

    private fun mapToNewsPreview(model: News): ShortNewsItem = with(model) {
        ShortNewsItem(
            title = title,
            imageUrl = imageUrl
        )
    }

    private fun mapToNewsFull(model: News): FullNewsItem = with(model) {
        FullNewsItem(title, date, imageUrl, description)
    }
}