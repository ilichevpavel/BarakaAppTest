package ru.ilichev.barakatestapp.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.ilichev.barakatestapp.feature.data.news.NewsRepository
import ru.ilichev.barakatestapp.feature.data.tickers.TickersRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val tickersRepository: TickersRepository,
    private val newsViewItemConverter: NewsViewItemConverter,
    private val tickersViewItemConverter: TickersViewItemConverter
) : ViewModel() {

    private val _state: MutableStateFlow<MainViewState> = MutableStateFlow(MainViewState.Loading)
    val state: StateFlow<MainViewState> = _state

    init {
        subscribeToTickers()
    }

    fun onAction(action: MainAction) {
        when (action) {
           MainAction.OnReplyLoadingClicked -> {
               // do something after error
           }
        }
    }

    private fun subscribeToTickers() {
        viewModelScope.launch {
            tickersRepository.loadTickers()
                .combine(newsRepository.getNews()) { tickers, news ->
                    val tickersViewItems = tickersViewItemConverter(tickers)
                    val newsViewItems = newsViewItemConverter(news)

                    MainViewState.Content(tickersViewItems + newsViewItems)
                }
                .catch { _state.value = MainViewState.Error }
                .collect { _state.value = it }
        }
    }
}