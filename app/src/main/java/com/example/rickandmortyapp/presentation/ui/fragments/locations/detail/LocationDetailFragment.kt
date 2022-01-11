package com.example.rickandmortyapp.presentation.ui.fragments.locations.detail

import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.base.BaseFragment
import com.example.rickandmortyapp.common.extensions.toFormatDate
import com.example.rickandmortyapp.databinding.FragmentLocationDetailBinding
import com.example.rickandmortyapp.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment :
    BaseFragment<FragmentLocationDetailBinding, LocationDetailViewModel>(
        R.layout.fragment_location_detail
    ) {

    override val binding by viewBinding(FragmentLocationDetailBinding::bind)
    override val viewModel by viewModels<LocationDetailViewModel>()
    private val args by navArgs<LocationDetailFragmentArgs>()

    override fun setupRequests() {
        viewModel.getLocationById(args.id)
    }

    override fun setupObserves() = with(binding) {
        viewModel.locationDetailState.observe(viewLifecycleOwner, {
            when (it) {
                is UIState.Loading -> {
                    Log.e("blade", "Loading...")
                }
                is UIState.Error -> {
                    Log.e("blade", it.error)
                }
                is UIState.Success -> {
                    it.data.apply {
                        detailName.text = name
                        detailType.text = type
                        detailDimension.text = dimension
                        detailCreated.text = toFormatDate(created)
                        imagePlanet.isVisible = type == "Planet"
                    }
                }
            }
        })
    }
}