package com.example.rickandmortyapp.presentation.ui.fragments.episodes

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.base.BaseFragment
import com.example.rickandmortyapp.common.extensions.hideKeyboard
import com.example.rickandmortyapp.common.extensions.setOnActionExpandListener
import com.example.rickandmortyapp.common.extensions.setTools
import com.example.rickandmortyapp.common.extensions.submitSearch
import com.example.rickandmortyapp.databinding.FragmentEpisodesBinding
import com.example.rickandmortyapp.presentation.ui.adapters.EpisodesAdapter
import com.example.rickandmortyapp.presentation.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding, EpisodesViewModel>(
    R.layout.fragment_episodes
) {

    override val binding by viewBinding(FragmentEpisodesBinding::bind)
    override val viewModel by viewModels<EpisodesViewModel>()
    private val adapter: EpisodesAdapter = EpisodesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun initialize() {
        setupRecycler()
    }

    private fun setupRecycler() = with(binding) {
        episodesRecycler.layoutManager = LinearLayoutManager(requireContext())
        episodesRecycler.adapter = adapter.withLoadStateFooter(LoadStateAdapter {
            adapter.retry()
        })
    }

    override fun setupViews() {
        setupProgressBar()
    }

    private fun setupProgressBar() {
        adapter.addLoadStateListener {
            if (view != null) {
                binding.episodesLoading.isVisible = it.refresh is LoadState.Loading
            }
        }
    }

    override fun setupRequests() {
        viewModel.getEpisodes("")
    }

    override fun setupObserves() {
        viewModel.episodesSate.observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setTools(context)

        searchView.submitSearch { viewModel.getEpisodes(it.toString()) }

        searchItem.setOnActionExpandListener(searchView) { hideKeyboard() }
    }
}