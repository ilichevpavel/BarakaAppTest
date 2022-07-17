package ru.ilichev.barakatestapp.feature.presentation.list

import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import ru.ilichev.barakatestapp.R
import ru.ilichev.barakatestapp.common.DelegateAdapter
import ru.ilichev.barakatestapp.common.loadImage
import ru.ilichev.barakatestapp.databinding.FullNewsItemBinding
import ru.ilichev.barakatestapp.databinding.RawContentItemBinding
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.FullNewsSection
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.FullNewsSection.FullNewsItem

object FullNewsDelegate {
    fun ContentWrapper() =
        adapterDelegate<FullNewsSection, ItemViewTyped>(R.layout.raw_content_item) {
            val binding = RawContentItemBinding.bind(itemView)
            binding.rvContent.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            val adapter = DelegateAdapter(FullNewsItemCallback()) {
                addDelegate(ContentItem())
            }
            binding.rvContent.adapter = adapter

            bind {
                adapter.items = item.news
            }
        }

    private fun ContentItem() =
        adapterDelegate<FullNewsItem, FullNewsItem>(R.layout.full_news_item) {
            val binding = FullNewsItemBinding.bind(itemView)

            bind {
                binding.tvTitle.text = item.title
                binding.tvDescription.text = item.description
                binding.tvDate.text = item.date
                binding.ivImage.loadImage(item.imageUrl)
            }
        }
}