package ru.ilichev.barakatestapp.feature.presentation

import ru.ilichev.barakatestapp.feature.domain.model.Ticker
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.TickersSection.TickerItem
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.TickersSection
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import javax.inject.Inject

private const val USD_PRICE_PATTERN = "%s USD"

class TickersViewItemConverter @Inject constructor() : (List<Ticker>) -> List<ItemViewTyped> {

    private val decimalFormat = DecimalFormat().apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
        isGroupingUsed = false
        decimalFormatSymbols = DecimalFormatSymbols().apply { decimalSeparator = '.' }
    }

    override fun invoke(tickers: List<Ticker>): List<ItemViewTyped> {
        if (tickers.isEmpty()) return emptyList()

        return listOf(TickersSection(tickers.map(::mapToViewItem)))
    }

    private fun mapToViewItem(model: Ticker): TickerItem = with(model) {
        TickerItem(
            symbol = stock,
            price = USD_PRICE_PATTERN.format(decimalFormat.format(price))
        )
    }
}
