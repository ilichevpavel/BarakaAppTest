package ru.ilichev.barakatestapp.feature.presentation.list

sealed class ItemViewTyped {

    data class TickersSection(val tickers: List<TickerItem>) : ItemViewTyped() {

        data class TickerItem(
            val symbolUrl: String,
            val price: String
        )
    }

    data class ShortNewsSection(val news: List<ShortNewsItem>) : ItemViewTyped() {

        data class ShortNewsItem(
            val title: String,
            val imageUrl: String
        )
    }

    data class FullNewsSection(val news: List<FullNewsItem>) : ItemViewTyped() {

        data class FullNewsItem(
            val title: String,
            val date: String,
            val imageUrl: String,
            val description: String
        )
    }
}