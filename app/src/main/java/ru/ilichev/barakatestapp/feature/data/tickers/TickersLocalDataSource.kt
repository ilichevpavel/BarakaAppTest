package ru.ilichev.barakatestapp.feature.data.tickers

import android.content.Context
import androidx.annotation.WorkerThread
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.random.Random

class TickersLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val stocks = mutableMapOf<String, MutableList<BigDecimal>>()

    @WorkerThread
    fun loadTickers(): Flow<List<TickerDto>> = flow {
        context.assets.open("stocks.csv").bufferedReader().use { reader ->
            // skip first line
            reader.readLine()
            var line: String? = reader.readLine()

            while (line != null) {
                val rowData = line.split(",").toTypedArray()

                stocks[rowData[0]] = stocks.getOrDefault(rowData[0], mutableListOf()).apply {
                    add(BigDecimal(rowData[1].trim()))
                }

                line = reader.readLine()
            }
        }

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