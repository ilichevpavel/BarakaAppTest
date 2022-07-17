package ru.ilichev.barakatestapp

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.ilichev.barakatestapp.feature.domain.model.Ticker
import ru.ilichev.barakatestapp.feature.presentation.TickersViewItemConverter
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.TickersSection.TickerItem
import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped.TickersSection
import java.math.BigDecimal

class TickersViewItemConverterTest {

    private val converter = TickersViewItemConverter()

    @Test
    fun `view items success mapping`() {
        val tickers = listOf(
            Ticker("FORD", BigDecimal("1.234124")),
            Ticker("NVDA", BigDecimal("4.123134")),
            Ticker("AMD", BigDecimal("32.41241234")),
            Ticker("NIO", BigDecimal("123.55555"))
        )

        val expected = listOf(
            TickersSection(
                listOf(
                    TickerItem("FORD", "1.23 USD"),
                    TickerItem("NVDA", "4.12 USD"),
                    TickerItem("AMD", "32.41 USD"),
                    TickerItem("NIO", "123.56 USD")
                )
            )
        )

        val actual = converter(tickers)

        assertEquals(expected, actual)
    }

    @Test
    fun `map ticker empty list`() {
        val tickers = emptyList<Ticker>()

        val expected = emptyList<ItemViewTyped>()
        val actual = converter(tickers)

        assertEquals(expected, actual)
    }
}