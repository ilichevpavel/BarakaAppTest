package ru.ilichev.barakatestapp.feature.data.tickers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.ilichev.barakatestapp.feature.domain.model.Ticker
import javax.inject.Inject

class TickersRepository @Inject constructor(
    private val localDataSource: TickersLocalDataSource
) {

    fun loadTickers(): Flow<List<Ticker>> {
        return localDataSource.loadTickers()
            .map { it.map(::mapToDomain) }
            .flowOn(Dispatchers.IO)
    }

    private fun mapToDomain(model: TickerDto): Ticker = with(model) {
        Ticker(stock, price)
    }
}