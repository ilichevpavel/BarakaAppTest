package ru.ilichev.barakatestapp.feature.presentation

import ru.ilichev.barakatestapp.feature.presentation.list.ItemViewTyped

sealed class MainViewState {

    object Loading : MainViewState()
    object Error : MainViewState()
    data class Content(val items: List<ItemViewTyped>) : MainViewState()
}