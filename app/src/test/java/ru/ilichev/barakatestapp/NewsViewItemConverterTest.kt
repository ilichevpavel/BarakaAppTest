package ru.ilichev.barakatestapp

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.ilichev.barakatestapp.feature.domain.model.News
import ru.ilichev.barakatestapp.feature.presentation.NewsViewItemConverter
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.ShortNewsSection
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.ShortNewsSection.ShortNewsItem
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.FullNewsItem

class NewsViewItemConverterTest {

    private val converter = NewsViewItemConverter()

    @Test
    fun `map news with less then max preview count`() {
        val news = listOf(
            News("title1", "2022-05-05T09:25:29Z", "imageUrl1", "description"),
            News("title2", "2022-05-05T09:25:29Z", "imageUrl2", "description"),
            News("title3", "2022-05-05T09:25:29Z", "imageUrl3", "description"),
            News("title4", "2022-05-05T09:25:29Z", "imageUrl4", "description"),
            News("title5", "2022-05-05T09:25:29Z", "imageUrl5", "description")
        )

        val expected = listOf(
            ShortNewsSection(
                listOf(
                    ShortNewsItem("title1", "imageUrl1"),
                    ShortNewsItem("title2", "imageUrl2"),
                    ShortNewsItem("title3", "imageUrl3"),
                    ShortNewsItem("title4", "imageUrl4"),
                    ShortNewsItem("title5", "imageUrl5")
                )
            )
        )

        val actual = converter(news)

        assertEquals(expected, actual)
    }

    @Test
    fun `map news with more then max preview count`() {
        val news = listOf(
            News("title1", "2022-05-05T09:25:29Z", "imageUrl1", "description"),
            News("title2", "2022-05-05T09:25:29Z", "imageUrl2", "description"),
            News("title3", "2022-05-05T09:25:29Z", "imageUrl3", "description"),
            News("title4", "2022-05-05T09:25:29Z", "imageUrl4", "description"),
            News("title5", "2022-05-05T09:25:29Z", "imageUrl5", "description"),
            News("title6", "2022-05-05T09:25:29Z", "imageUrl6", "description"),
            News("title7", "2022-05-05T09:25:29Z", "imageUrl7", "description"),
            News("title8", "2022-05-05T09:25:29Z", "imageUrl8", "description"),
            News("title9", "2022-05-05T09:25:29Z", "imageUrl9", "description")
        )

        val expected = listOf(
            ShortNewsSection(
                listOf(
                    ShortNewsItem("title1", "imageUrl1"),
                    ShortNewsItem("title2", "imageUrl2"),
                    ShortNewsItem("title3", "imageUrl3"),
                    ShortNewsItem("title4", "imageUrl4"),
                    ShortNewsItem("title5", "imageUrl5"),
                    ShortNewsItem("title6", "imageUrl6")
                )
            ),
            FullNewsItem("title7", "2022-05-05T09:25:29Z", "imageUrl7", "description"),
            FullNewsItem("title8", "2022-05-05T09:25:29Z", "imageUrl8", "description"),
            FullNewsItem("title9", "2022-05-05T09:25:29Z", "imageUrl9", "description")
        )

        val actual = converter(news)

        assertEquals(expected, actual)
    }

    @Test
    fun `map empty news list`() {
        val news = emptyList<News>()

        val expected = emptyList<ItemViewTyped>()
        val actual = converter(news)

        assertEquals(expected, actual)
    }
}