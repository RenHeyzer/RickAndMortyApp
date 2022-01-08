package com.example.rickandmortyapp.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.rickandmortyapp.base.BaseRecyclerViewHolder
import com.example.rickandmortyapp.common.extensions.toFormatDate
import com.example.rickandmortyapp.databinding.ItemLocationsBinding
import com.example.rickandmortyapp.domain.models.RickAndMorty

class LocationsAdapter :
    PagingDataAdapter<RickAndMorty.LocationsItem, BaseRecyclerViewHolder<ViewBinding, RickAndMorty.LocationsItem>>(
        LocationDiffUtil()
    ) {

    class LocationDiffUtil : DiffUtil.ItemCallback<RickAndMorty.LocationsItem>() {

        override fun areItemsTheSame(
            oldItem: RickAndMorty.LocationsItem,
            newItem: RickAndMorty.LocationsItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: RickAndMorty.LocationsItem,
            newItem: RickAndMorty.LocationsItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<ViewBinding, RickAndMorty.LocationsItem> {
        return LocationsViewHolder(
            ItemLocationsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: BaseRecyclerViewHolder<ViewBinding, RickAndMorty.LocationsItem>,
        position: Int
    ) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class LocationsViewHolder(
        binding: ItemLocationsBinding
    ) : BaseRecyclerViewHolder<ItemLocationsBinding, RickAndMorty.LocationsItem>(binding) {

        override fun onBind(item: RickAndMorty.LocationsItem) = with(binding) {
            itemName.text = item.name
            itemType.text = item.type
            imagePlanet.isVisible = item.type == "Planet"
            itemCreated.text = toFormatDate(item.created)
            itemDimension.text = item.dimension
        }
    }
}