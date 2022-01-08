package com.example.rickandmortyapp.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.base.BaseRecyclerViewHolder
import com.example.rickandmortyapp.common.extensions.loadImage
import com.example.rickandmortyapp.common.extensions.setOnSingleClickListener
import com.example.rickandmortyapp.databinding.ItemCharactersBinding
import com.example.rickandmortyapp.domain.models.RickAndMorty

class CharactersAdapter(val onClick: (id: Int?) -> Unit) :
    PagingDataAdapter<RickAndMorty.CharactersItem, BaseRecyclerViewHolder<ViewBinding, RickAndMorty.CharactersItem>>(
        CharacterDiffUtil()
    ) {

    class CharacterDiffUtil : DiffUtil.ItemCallback<RickAndMorty.CharactersItem>() {

        override fun areItemsTheSame(
            oldItem: RickAndMorty.CharactersItem,
            newItem: RickAndMorty.CharactersItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: RickAndMorty.CharactersItem,
            newItem: RickAndMorty.CharactersItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<ViewBinding, RickAndMorty.CharactersItem> {
        return CharactersViewHolder(
            ItemCharactersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: BaseRecyclerViewHolder<ViewBinding, RickAndMorty.CharactersItem>,
        position: Int
    ) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CharactersViewHolder(
        binding: ItemCharactersBinding
    ) : BaseRecyclerViewHolder<ItemCharactersBinding, RickAndMorty.CharactersItem>(binding) {

        override fun onBind(item: RickAndMorty.CharactersItem) = with(binding) {
            itemName.text = item.name
            itemImage.loadImage(item.image)
            itemStatus.text = item.status
            itemSpecies.text = item.species
            itemLocation.text = item.location.name
            itemEpisode.text = item.origin.name

            when (item.status) {
                "Alive" -> statusDot.setImageResource(R.drawable.ic_dot)

                "Dead" -> statusDot.setImageResource(R.drawable.ic_dot_red)

                "unknown" -> statusDot.setImageResource(R.drawable.ic_dot_gray)
            }
            itemView.setOnSingleClickListener {
                getItem(absoluteAdapterPosition)?.apply {
                    onClick(this.id)
                }
            }
        }
    }
}