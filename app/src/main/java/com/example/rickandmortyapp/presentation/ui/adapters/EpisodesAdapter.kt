package com.example.rickandmortyapp.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.rickandmortyapp.base.BaseRecyclerViewHolder
import com.example.rickandmortyapp.common.extensions.toFormatDate
import com.example.rickandmortyapp.databinding.ItemEpisodesBinding
import com.example.rickandmortyapp.domain.models.RickAndMorty

class EpisodesAdapter :
    PagingDataAdapter<RickAndMorty.EpisodesItem, BaseRecyclerViewHolder<ViewBinding, RickAndMorty.EpisodesItem>>(
        EpisodeDiffUtil()
    ) {

    class EpisodeDiffUtil : DiffUtil.ItemCallback<RickAndMorty.EpisodesItem>() {

        override fun areItemsTheSame(
            oldItem: RickAndMorty.EpisodesItem,
            newItem: RickAndMorty.EpisodesItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: RickAndMorty.EpisodesItem,
            newItem: RickAndMorty.EpisodesItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<ViewBinding, RickAndMorty.EpisodesItem> {
        return EpisodesViewHolder(
            ItemEpisodesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: BaseRecyclerViewHolder<ViewBinding, RickAndMorty.EpisodesItem>,
        position: Int
    ) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class EpisodesViewHolder(
        binding: ItemEpisodesBinding
    ) : BaseRecyclerViewHolder<ItemEpisodesBinding, RickAndMorty.EpisodesItem>(binding) {

        override fun onBind(item: RickAndMorty.EpisodesItem) = with(binding) {
            itemName.text = item.name
            itemEpisode.text = item.episode
                ?.replace("S", "Season ")
                ?.replace("E", " Episode ")
            itemCreated.text = toFormatDate(item.created)
            itemAirDate.text = item.airDate
        }
    }
}