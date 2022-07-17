package ru.ilichev.barakatestapp.feature.presentation.list

import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import ru.ilichev.barakatestapp.R
import ru.ilichev.barakatestapp.common.DelegateAdapter
import ru.ilichev.barakatestapp.common.loadImage
import ru.ilichev.barakatestapp.databinding.RawContentItemBinding
import ru.ilichev.barakatestapp.databinding.ShortNewsItemBinding
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.ShortNewsSection
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.ShortNewsSection.ShortNewsItem

object ShortNewsDelegate {
    fun ContentWrapper() =
        adapterDelegate<ShortNewsSection, ItemViewTyped>(R.layout.raw_content_item) {
            val binding = RawContentItemBinding.bind(itemView)
            binding.rvContent.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            val adapter = DelegateAdapter(ShortNewsItemCallback()) {
                addDelegate(ContentItem())
            }
            binding.rvContent.adapter = adapter

            bind {
                adapter.items = item.news
            }
        }

    private fun ContentItem() =
        adapterDelegate<ShortNewsItem, ShortNewsItem>(R.layout.full_news_item) {
            val binding = ShortNewsItemBinding.bind(itemView)

            bind {
                binding.tvTitle.text = item.title
                binding.ivImage.loadImage(item.imageUrl)
            }
        }
}