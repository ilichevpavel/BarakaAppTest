package ru.ilichev.barakatestapp.feature.presentation

import ru.ilichev.barakatestapp.feature.domain.model.Ticker
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.TickersSection.TickerItem
import java.text.DecimalFormat
import javax.inject.Inject

private const val USD_PRICE_PATTERN = "%s USD"

class TickersViewItemConverter @Inject constructor() : (List<Ticker>) -> List<ItemViewTyped> {

    private val decimalFormat = DecimalFormat().apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
        isGroupingUsed = true
    }

    override fun invoke(tickers: List<Ticker>): List<ItemViewTyped> {
        return listOf(ItemViewTyped.TickersSection(tickers.map(::mapToViewItem)))
    }

    private fun mapToViewItem(model: Ticker): TickerItem = with(model) {
        TickerItem(
            symbolUrl = stock,
            price = USD_PRICE_PATTERN.format(decimalFormat.format(price))
        )
    }
}
