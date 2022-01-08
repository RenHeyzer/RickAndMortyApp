package com.example.rickandmortyapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.ItemCharactersBinding
import com.example.rickandmortyapp.databinding.ItemEpisodesBinding
import com.example.rickandmortyapp.databinding.ItemLocationsBinding
import com.example.rickandmortyapp.domain.models.RickAndMorty

class SearchAdapter :
    PagingDataAdapter<RickAndMorty, SearchRecyclerViewHolder<ViewBinding>>(
        SearchDiffUtil()
    ) {

    class SearchDiffUtil : DiffUtil.ItemCallback<RickAndMorty>() {

        override fun areItemsTheSame(oldItem: RickAndMorty, newItem: RickAndMorty): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RickAndMorty, newItem: RickAndMorty): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchRecyclerViewHolder<ViewBinding> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_characters -> SearchRecyclerViewHolder.CharactersViewHolder(
                ItemCharactersBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            R.layout.item_locations -> SearchRecyclerViewHolder.LocationsViewHolder(
                ItemLocationsBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            R.layout.item_episodes -> SearchRecyclerViewHolder.EpisodesViewHolder(
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
        when (holder) {
            is SearchRecyclerViewHolder.CharactersViewHolder ->
                holder.onBind(getItem(position) as RickAndMorty.CharactersItem)
            is SearchRecyclerViewHolder.LocationsViewHolder ->
                holder.onBind(getItem(position) as RickAndMorty.LocationsItem)
            is SearchRecyclerViewHolder.EpisodesViewHolder ->
                holder.onBind(getItem(position) as RickAndMorty.EpisodesItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is RickAndMorty.CharactersItem -> R.layout.item_characters
            is RickAndMorty.LocationsItem -> R.layout.item_locations
            is RickAndMorty.EpisodesItem -> R.layout.item_episodes
            null -> throw IllegalStateException("Unknown view")
        }
    }
}

//inner class CharactersViewHolder(
//    binding: ItemCharactersBinding
//) : BaseRecyclerViewHolder<ItemCharactersBinding, RickAndMorty.CharactersItem>(binding) {
//
//    override fun onBind(item: RickAndMorty.CharactersItem) = with(binding) {
//        itemName.text = item.characters.name
//        itemImage.loadImage(item.characters.image)
//        itemStatus.text = item.characters.status
//        itemSpecies.text = item.characters.species
//        itemLocation.text = item.characters.location.name
//        itemEpisode.text = item.characters.origin.name
//
//        when (item.characters.status) {
//
//            "Alive" -> statusDot.setImageResource(R.drawable.ic_dot)
//
//            "Dead" -> statusDot.setImageResource(R.drawable.ic_dot_red)
//
//            "unknown" -> statusDot.setImageResource(R.drawable.ic_dot_gray)
//        }
//    }
//}
//
//inner class LocationsViewHolder(
//    binding: ItemLocationsBinding
//) : BaseRecyclerViewHolder<ItemLocationsBinding, RickAndMorty.LocationsItem>(binding) {
//
//    override fun onBind(item: RickAndMorty.LocationsItem) = with(binding) {
//        itemName.text = item.locations.name
//        itemType.text = item.locations.type
//        imagePlanet.isVisible = item.locations.type == "Planet"
//        itemCreated.text = toFormatDate(item.locations.created)
//        itemDimension.text = item.locations.dimension
//    }
//}
//
//inner class EpisodesViewHolder(
//    binding: ItemEpisodesBinding
//) : BaseRecyclerViewHolder<ItemEpisodesBinding, RickAndMorty.EpisodesItem>(binding) {
//
//    override fun onBind(item: RickAndMorty.EpisodesItem) = with(binding) {
//        itemName.text = item.episodes.name
//        itemEpisode.text = item.episodes.episode
//            ?.replace("S", "Season ")
//            ?.replace("E", " Episode ")
//        itemCreated.text = toFormatDate(item.episodes.created)
//        itemAirDate.text = item.episodes.airDate
//    }
//}