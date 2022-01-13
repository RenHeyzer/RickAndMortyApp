package com.example.rickandmortyapp.presentation.ui.fragments.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.base.BaseFragment
import com.example.rickandmortyapp.common.extensions.hideKeyboard
import com.example.rickandmortyapp.common.extensions.setOnActionExpandListener
import com.example.rickandmortyapp.common.extensions.setTools
import com.example.rickandmortyapp.common.extensions.submitSearch
import com.example.rickandmortyapp.databinding.FragmentSearchBinding
import com.example.rickandmortyapp.presentation.state.UIState
import com.example.rickandmortyapp.presentation.ui.adapters.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(
    R.layout.fragment_search
) {
    override val binding by viewBinding(FragmentSearchBinding::bind)
    override val viewModel by viewModels<SearchViewModel>()
    private val adapter: SearchAdapter = SearchAdapter(
        this::onItemCharacterClick,
        this::onItemLocationClick,
        this::onItemEpisodeClick
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun initialize() {
        setupRecycler()
    }

    private fun setupRecycler() = with(binding) {
        searchRecycler.layoutManager = LinearLayoutManager(requireContext())
        searchRecycler.adapter = adapter
        registerScroll(adapter)
    }

    override fun setupRequests() {
        viewModel.processAllRequests("")
    }

    override fun setupObserves() {
        binding.apply {
            viewModel.generalState.observe(viewLifecycleOwner, {
                searchLoading.isVisible = it is UIState.Loading
                when (it) {
                    is UIState.Loading -> {
                    }
                    is UIState.Error -> {
                    }
                    is UIState.Success -> {
                        val sortedList =
                            it.data.sortedByDescending { date -> date.created }
                        adapter.submitList(sortedList)
                    }
                }
            })
        }
    }

    private fun registerScroll(adapter: SearchAdapter) = with(binding.searchRecycler) {
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                scrollToPosition(0)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val filterItem = menu.findItem(R.id.filter)
        val searchView = searchItem.actionView as SearchView

        filterItem.isVisible = false

        searchView.setTools(context)

        searchView.submitSearch { viewModel.processAllRequests(it.toString()) }

        searchItem.setOnActionExpandListener(searchView) { hideKeyboard() }
    }

    private fun onItemCharacterClick(id: Int, name: String) {
        val action =
            SearchFragmentDirections.actionSearchFragmentToCharacterDetailFragment(id, name)
        findNavController().navigate(action)
    }

    private fun onItemLocationClick(id: Int, name: String) {
        val action =
            SearchFragmentDirections.actionSearchFragmentToLocationDetailFragment(id, name)
        findNavController().navigate(action)
    }

    private fun onItemEpisodeClick(id: Int, name: String) {
        val action =
            SearchFragmentDirections.actionSearchFragmentToEpisodeDetailFragment(id, name)
        findNavController().navigate(action)
    }
}