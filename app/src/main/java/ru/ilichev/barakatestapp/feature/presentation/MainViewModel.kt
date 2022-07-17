package ru.ilichev.barakatestapp.feature.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun onAction(action: MainAction) {
        TODO()
    }
}