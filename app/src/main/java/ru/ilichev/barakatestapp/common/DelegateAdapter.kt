package ru.ilichev.barakatestapp.common

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class DelegateAdapter<T>(
    callback: DiffUtil.ItemCallback<T>,
    delegateBuilder: AdapterDelegatesManager<List<T>>.() -> Unit
) : AsyncListDifferDelegationAdapter<T>(callback) {

    init {
        delegateBuilder(delegatesManager)
    }
}