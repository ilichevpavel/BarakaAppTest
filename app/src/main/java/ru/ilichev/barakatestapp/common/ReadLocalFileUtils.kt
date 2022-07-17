package ru.ilichev.barakatestapp.common

import android.content.Context
import java.io.IOException

@Throws(IOException::class)
fun getJsonDataFromAsset(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}