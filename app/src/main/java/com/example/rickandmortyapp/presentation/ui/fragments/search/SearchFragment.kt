package com.example.rickandmortyapp.presentation.ui.fragments.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.base.BaseFragment
import com.example.rickandmortyapp.common.extensions.hideKeyboard
import com.example.rickandmortyapp.common.extensions.setOnActionExpandListener
import com.example.rickandmortyapp.common.extensions.setTools
import com.example.rickandmortyapp.common.extensions.submitSearch
import com.example.rickandmortyapp.databinding.FragmentSearchBinding
import com.example.rickandmortyapp.presentation.ui.adapters.SearchAdapter
import com.example.rickandmortyapp.presentation.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(
    R.layout.fragment_search
) {
    override val binding by viewBinding(FragmentSearchBinding::bind)
    override val viewModel by viewModels<SearchViewModel>()
    private val adapter: SearchAdapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun initialize() {
        setupRecycler()
    }

    private fun setupRecycler() = with(binding) {
        searchRecycler.layoutManager = LinearLayoutManager(requireContext())
        searchRecycler.adapter = adapter.withLoadStateFooter(LoadStateAdapter {
            adapter.retry()
        })
    }

    override fun setupRequests() {
        viewModel.processAllRequest("")
    }

    override fun setupObserves() {
        viewModel.allState.observe(viewLifecycleOwner, {
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

        searchView.submitSearch { viewModel.processAllRequest(it.toString()) }

        searchItem.setOnActionExpandListener(searchView) { hideKeyboard() }
    }
}