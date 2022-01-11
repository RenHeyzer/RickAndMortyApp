package com.example.rickandmortyapp.presentation.ui.fragments.characters

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.base.BaseFragment
import com.example.rickandmortyapp.common.extensions.hideKeyboard
import com.example.rickandmortyapp.common.extensions.setOnActionExpandListener
import com.example.rickandmortyapp.common.extensions.setTools
import com.example.rickandmortyapp.common.extensions.submitSearch
import com.example.rickandmortyapp.databinding.FragmentCharactersBinding
import com.example.rickandmortyapp.presentation.ui.adapters.CharactersAdapter
import com.example.rickandmortyapp.presentation.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>(
    R.layout.fragment_characters
) {

    override val binding by viewBinding(FragmentCharactersBinding::bind)
    override val viewModel by viewModels<CharactersViewModel>()
    private val adapter = CharactersAdapter(this::onClickToDetail)
    private val args by navArgs<CharactersFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun initialize() {
        setupRecycler()
    }

    private fun setupRecycler() = with(binding) {
        binding.charactersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRecycler.adapter = adapter.withLoadStateFooter(LoadStateAdapter {
            adapter.retry()
        })
    }

    override fun setupViews() {
        setupProgressBar()
    }

    private fun setupProgressBar() = with(binding) {
        adapter.addLoadStateListener {
            if (view != null) {
                charactersLoading.isVisible = it.refresh is LoadState.Loading
                charactersRecycler.isVisible = it.refresh !is LoadState.Error
                container.containerNotFound.isVisible =
                    it.refresh is LoadState.Error && it.append is LoadState.NotLoading
            }
        }
    }

    override fun setupRequests() {
        if (args.status == "" && args.gender == "") {
            viewModel.getCharacters("")
        } else {
            viewModel.getCharactersWithFilter("", args.status, args.gender)
        }
    }

    override fun setupObserves() {
        if (args.status == "" && args.gender == "") {
            viewModel.charactersState.observe(viewLifecycleOwner, {
                lifecycleScope.launch {
                    adapter.submitData(it)
                }
            })
        } else {
            viewModel.charactersFilterState.observe(viewLifecycleOwner, {
                lifecycleScope.launch {
                    adapter.submitData(it)
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setTools(context)

        if (args.status == "" && args.gender == "") {
            searchView.submitSearch { viewModel.getCharacters(it.toString()) }
        } else {
            searchView.submitSearch {
                viewModel.getCharactersWithFilter(
                    it.toString(),
                    args.status,
                    args.gender
                )
            }
        }

        searchItem.setOnActionExpandListener(searchView) { hideKeyboard() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filter) {
            findNavController().navigate(
                CharactersFragmentDirections.actionCharactersFragmentToFilterDialogFragment(
                    getString(R.string.characters_filter_type)
                )
            )
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onClickToDetail(id: Int, name: String) {
        val action = CharactersFragmentDirections
            .actionCharactersFragmentToCharacterDetailFragment(id, name)
        findNavController().navigate(action)
    }
}