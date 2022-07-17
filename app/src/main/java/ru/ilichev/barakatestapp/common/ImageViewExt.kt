package ru.ilichev.barakatestapp.common

import android.widget.ImageView
import coil.load

fun ImageView.loadImage(
    url: String
) {
    load(url)
}