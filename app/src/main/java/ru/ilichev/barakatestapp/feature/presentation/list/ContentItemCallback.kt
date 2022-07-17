package ru.ilichev.barakatestapp.feature.presentation.list

import androidx.recyclerview.widget.DiffUtil

class ContentItemCallback : DiffUtil.ItemCallback<ItemViewTyped>() {

    override fun areItemsTheSame(oldItem: ItemViewTyped, newItem: ItemViewTyped): Boolean {
        return when {
            oldItem is ItemViewTyped.TickersSection && newItem is ItemViewTyped.TickersSection -> true
            oldItem is ItemViewTyped.FullNewsSection && newItem is ItemViewTyped.FullNewsSection -> true
            oldItem is ItemViewTyped.ShortNewsSection && newItem is ItemViewTyped.ShortNewsSection -> true
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: ItemViewTyped, newItem: ItemViewTyped): Boolean {
        return when {
            oldItem is ItemViewTyped.TickersSection && newItem is ItemViewTyped.TickersSection -> oldItem == newItem
            oldItem is ItemViewTyped.FullNewsSection && newItem is ItemViewTyped.FullNewsSection -> oldItem == newItem
            oldItem is ItemViewTyped.ShortNewsSection && newItem is ItemViewTyped.ShortNewsSection -> oldItem == newItem
            else -> false
        }
    }
}

class TickersItemCallback : DiffUtil.ItemCallback<ItemViewTyped.TickersSection.TickerItem>() {

    override fun areItemsTheSame(
        oldItem: ItemViewTyped.TickersSection.TickerItem,
        newItem: ItemViewTyped.TickersSection.TickerItem
    ): Boolean {
        return oldItem.symbolUrl == newItem.symbolUrl
    }

    override fun areContentsTheSame(
        oldItem: ItemViewTyped.TickersSection.TickerItem,
        newItem: ItemViewTyped.TickersSection.TickerItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(
        oldItem: ItemViewTyped.TickersSection.TickerItem,
        newItem: ItemViewTyped.TickersSection.TickerItem
    ): Any? {
        return if (oldItem.symbolUrl == newItem.symbolUrl && oldItem.price != newItem.price) {
            newItem
        } else {
            super.getChangePayload(oldItem, newItem)
        }
    }
}

class ShortNewsItemCallback :
    DiffUtil.ItemCallback<ItemViewTyped.ShortNewsSection.ShortNewsItem>() {

    override fun areItemsTheSame(
        oldItem: ItemViewTyped.ShortNewsSection.ShortNewsItem,
        newItem: ItemViewTyped.ShortNewsSection.ShortNewsItem
    ): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(
        oldItem: ItemViewTyped.ShortNewsSection.ShortNewsItem,
        newItem: ItemViewTyped.ShortNewsSection.ShortNewsItem
    ): Boolean {
        return oldItem == newItem
    }
}

class FullNewsItemCallback : DiffUtil.ItemCallback<ItemViewTyped.FullNewsSection.FullNewsItem>() {

    override fun areItemsTheSame(
        oldItem: ItemViewTyped.FullNewsSection.FullNewsItem,
        newItem: ItemViewTyped.FullNewsSection.FullNewsItem
    ): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(
        oldItem: ItemViewTyped.FullNewsSection.FullNewsItem,
        newItem: ItemViewTyped.FullNewsSection.FullNewsItem
    ): Boolean {
        return oldItem == newItem
    }
}