package ru.ilichev.barakatestapp.feature.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
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
        addDelegate(FullNewsDelegate.ContentWrapper())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun renderState(state: MainViewState) = with(binding) {
        when (state) {
            MainViewState.Loading -> {
                progress.setVisibility(true)
                errorStub.setVisibility(false)
                rvContent.setVisibility(false)
            }
            MainViewState.Error -> {
                progress.setVisibility(false)
                errorStub.setVisibility(true)
                rvContent.setVisibility(false)
            }
            is MainViewState.Content -> {
                progress.setVisibility(false)
                errorStub.setVisibility(false)
                rvContent.setVisibility(true)

                contentAdapter.items = state.items
            }
        }
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