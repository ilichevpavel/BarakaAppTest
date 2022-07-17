package ru.ilichev.barakatestapp.feature.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.ilichev.barakatestapp.R
import ru.ilichev.barakatestapp.common.DelegateAdapter
import ru.ilichev.barakatestapp.common.setVisibility
import ru.ilichev.barakatestapp.databinding.ActivityMainBinding
import ru.ilichev.barakatestapp.feature.presentation.list.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val contentAdapter = DelegateAdapter(ContentItemCallback()) {
        addDelegate(TickersDelegates.ContentWrapper())
        addDelegate(ShortNewsDelegate.ContentWrapper())
        addDelegate(FullNewsDelegate.ContentItem())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect(::renderState)
            }
        }
    }

    private fun renderState(state: MainViewState) = with(binding) {
        updateContentVisibility(state)

        if (state is MainViewState.Content) {
            contentAdapter.items = state.items
        }
    }

    private fun updateContentVisibility(state: MainViewState) = with(binding) {
        progress.setVisibility(state is MainViewState.Loading)
        errorStub.setVisibility(state is MainViewState.Error)
        rvContent.setVisibility(state is MainViewState.Content)
    }

    private fun initViews() = with(binding) {
        btnReply.setOnClickListener { viewModel.onAction(MainAction.OnReplyLoadingClicked) }
        rvContent.adapter = contentAdapter
        rvContent.layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }
}