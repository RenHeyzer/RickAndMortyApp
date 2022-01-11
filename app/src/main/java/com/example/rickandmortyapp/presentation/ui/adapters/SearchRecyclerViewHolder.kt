package com.example.rickandmortyapp.presentation.ui.adapters

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.common.extensions.loadImage
import com.example.rickandmortyapp.common.extensions.setOnSingleClickListener
import com.example.rickandmortyapp.common.extensions.toFormatDate
import com.example.rickandmortyapp.databinding.ItemCharactersBinding
import com.example.rickandmortyapp.databinding.ItemEpisodesBinding
import com.example.rickandmortyapp.databinding.ItemLocationsBinding
import com.example.rickandmortyapp.domain.models.RickAndMorty

sealed class SearchRecyclerViewHolder<out V : ViewBinding>(binding: V) :
    RecyclerView.ViewHolder(binding.root) {

    class CharactersViewHolder(
        private val onItemClick: (id: Int, name: String) -> Unit,
        private val binding: ItemCharactersBinding
    ) : SearchRecyclerViewHolder<ItemCharactersBinding>(binding) {

        fun onBind(item: RickAndMorty.GeneralItem) = with(binding) {
            itemName.text = item.name
            itemImage.loadImage(item.image)
            itemStatus.text = item.status
            itemSpecies.text = item.species
            itemLocation.text = item.location?.name
            itemEpisode.text = item.origin?.name

            when (item.status) {

                "Alive" -> statusDot.setImageResource(R.drawable.ic_dot)

                "Dead" -> statusDot.setImageResource(R.drawable.ic_dot_red)

                "unknown" -> statusDot.setImageResource(R.drawable.ic_dot_gray)
            }
            itemView.setOnSingleClickListener {
                item.id?.let { id ->
                    item.name?.let { name ->
                        onItemClick(id, name)
                    }
                }
            }
        }
    }

    class LocationsViewHolder(
        private val onItemClick: (id: Int, name: String) -> Unit,
        private val binding: ItemLocationsBinding
    ) : SearchRecyclerViewHolder<ItemLocationsBinding>(binding) {

        fun onBind(item: RickAndMorty.GeneralItem) = with(binding) {
            itemName.text = item.name
            itemType.text = item.type
            imagePlanet.isVisible = item.type == "Planet"
            itemCreated.text = toFormatDate(item.created)
            itemDimension.text = item.dimension

            itemView.setOnSingleClickListener {
                item.id?.let { id ->
                    item.name?.let { name ->
                        onItemClick(id, name)
                    }
                }
            }
        }
    }

    class EpisodesViewHolder(
        private val onItemClick: (id: Int, name: String) -> Unit,
        private val binding: ItemEpisodesBinding
    ) : SearchRecyclerViewHolder<ItemEpisodesBinding>(binding) {

        fun onBind(item: RickAndMorty.GeneralItem) = with(binding) {
            itemName.text = item.name
            itemEpisode.text = item.episode
                ?.replace("S", "Season ")
                ?.replace("E", " Episode ")
            itemCreated.text = toFormatDate(item.created)
            itemAirDate.text = item.airDate

            itemView.setOnSingleClickListener {
                item.id?.let { id ->
                    item.name?.let { name ->
                        onItemClick(id, name)
                    }
                }
            }
        }
    }
}