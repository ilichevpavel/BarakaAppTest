package ru.ilichev.barakatestapp.feature.data.tickers

import androidx.annotation.WorkerThread
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import ru.ilichev.barakatestapp.common.CsvParser
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.random.Random

class TickersLocalDataSource @Inject constructor(
    private val csvParser: CsvParser
) {

    private val stocks = mutableMapOf<String, MutableList<BigDecimal>>()

    @WorkerThread
    fun loadTickers(): Flow<List<TickerDto>> = flow {
        stocks.putAll(
            csvParser.parse(
                csvFileName = "stocks.csv",
                keyMapping = { it },
                valuesMapping = { BigDecimal(it.trim()) }
            )
        )

        while (currentCoroutineContext().isActive) {
            emit(
                stocks.map { entry ->
                    TickerDto(entry.key, entry.value[Random.nextInt(entry.value.size)])
                }
            )

            delay(1_000)
        }
    }
}