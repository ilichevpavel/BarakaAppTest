package ru.ilichev.barakatestapp.common

import android.content.Context

class CsvParser(private val context: Context) {

    fun <K, V> parse(
        csvFileName: String,
        keyMapping: (String) -> K,
        valuesMapping: (String) -> V
    ): Map<K, MutableList<V>> {
        val result = mutableMapOf<K, MutableList<V>>()

        context.assets.open(csvFileName).bufferedReader().use { reader ->
            // skip first line with column lines
            reader.readLine()
            var line: String? = reader.readLine()

            while (line != null) {
                val rowData = line.split(",").toTypedArray()

                val key = keyMapping(rowData[0])
                val value = valuesMapping(rowData[1])
                result[key] = result.getOrDefault(key, mutableListOf()).apply { add(value) }

                line = reader.readLine()
            }
        }

        return result
    }
}