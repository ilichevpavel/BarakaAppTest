package ru.ilichev.barakatestapp.feature.presentation.list

import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import ru.ilichev.barakatestapp.R
import ru.ilichev.barakatestapp.common.DelegateAdapter
import ru.ilichev.barakatestapp.common.loadImage
import ru.ilichev.barakatestapp.databinding.RawContentItemBinding
import ru.ilichev.barakatestapp.databinding.TickerItemBinding
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.TickersSection
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.TickersSection.TickerItem

object TickersDelegates {
    fun ContentWrapper() =
        adapterDelegate<TickersSection, ItemViewTyped>(R.layout.raw_content_item) {
            val binding = RawContentItemBinding.bind(itemView)
            binding.rvContent.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            val adapter = DelegateAdapter(TickersItemCallback()) {
                addDelegate(ContentItem())
            }
            binding.rvContent.adapter = adapter

            bind {
                adapter.items = item.tickers
            }
        }

    private fun ContentItem() = adapterDelegate<TickerItem, TickerItem>(R.layout.ticker_item) {
        val binding = TickerItemBinding.bind(itemView)

        bind {
            it.mapNotNull { it as? TickerItem }.firstOrNull()?.let { item ->
                binding.tvPrice.text = item.price
            } ?: run {
                binding.tvPrice.text = item.price
                binding.ivIcon.loadImage(item.symbolUrl)
            }
        }
    }
}