package ru.ilichev.barakatestapp.feature.presentation.list

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import ru.ilichev.barakatestapp.R
import ru.ilichev.barakatestapp.common.loadImage
import ru.ilichev.barakatestapp.databinding.FullNewsItemBinding
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.FullNewsItem

object FullNewsDelegate {
    fun ContentItem() =
        adapterDelegate<FullNewsItem, ItemViewTyped>(R.layout.full_news_item) {
            val binding = FullNewsItemBinding.bind(itemView)

            bind {
                binding.tvTitle.text = item.title
                binding.tvDescription.text = item.description
                binding.tvDate.text = item.date
                binding.ivImage.loadImage(item.imageUrl)
            }
        }
}