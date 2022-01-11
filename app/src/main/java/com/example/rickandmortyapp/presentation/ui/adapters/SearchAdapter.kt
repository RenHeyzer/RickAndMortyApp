package com.example.rickandmortyapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.base.BaseDiffUtilItemCallback
import com.example.rickandmortyapp.databinding.ItemCharactersBinding
import com.example.rickandmortyapp.databinding.ItemEpisodesBinding
import com.example.rickandmortyapp.databinding.ItemLocationsBinding
import com.example.rickandmortyapp.domain.models.RickAndMorty

class SearchAdapter(
    private val onItemCharacterClick: (id: Int, name: String) -> Unit,
    private val onItemLocationClick: (id: Int, name: String) -> Unit,
    private val onItemEpisodeClick: (id: Int, name: String) -> Unit
) :
    ListAdapter<RickAndMorty.GeneralItem, SearchRecyclerViewHolder<ViewBinding>>(
        BaseDiffUtilItemCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchRecyclerViewHolder<ViewBinding> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_characters -> SearchRecyclerViewHolder.CharactersViewHolder(
                onItemCharacterClick,
                ItemCharactersBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            R.layout.item_locations -> SearchRecyclerViewHolder.LocationsViewHolder(
                onItemLocationClick,
                ItemLocationsBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            R.layout.item_episodes -> SearchRecyclerViewHolder.EpisodesViewHolder(
                onItemEpisodeClick,
                ItemEpisodesBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> throw IllegalAccessException("Invalid viewType provided")
        }
    }

    override fun onBindViewHolder(
        holder: SearchRecyclerViewHolder<ViewBinding>,
        position: Int
    ) {
        when (getItemViewType(position)) {
            R.layout.item_characters -> (
                    holder as SearchRecyclerViewHolder.CharactersViewHolder
                    ).onBind(getItem(position))
            R.layout.item_locations -> (
                    holder as SearchRecyclerViewHolder.LocationsViewHolder
                    ).onBind(getItem(position))
            R.layout.item_episodes -> (
                    holder as SearchRecyclerViewHolder.EpisodesViewHolder
                    ).onBind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            getItem(position).status?.isNotEmpty() == true -> {
                R.layout.item_characters
            }
            getItem(position).airDate?.isNotEmpty() == true -> {
                R.layout.item_episodes
            }
            else -> {
                R.layout.item_locations
            }
        }
    }
}