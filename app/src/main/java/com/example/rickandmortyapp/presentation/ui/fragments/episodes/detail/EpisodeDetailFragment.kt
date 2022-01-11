package com.example.rickandmortyapp.presentation.ui.fragments.episodes.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.base.BaseFragment
import com.example.rickandmortyapp.common.extensions.toFormatDate
import com.example.rickandmortyapp.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmortyapp.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment :
    BaseFragment<FragmentEpisodeDetailBinding, EpisodeDetailViewModel>(
        R.layout.fragment_episode_detail
    ) {

    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)
    override val viewModel by viewModels<EpisodeDetailViewModel>()
    private val args by navArgs<EpisodeDetailFragmentArgs>()

    override fun setupRequests() {
        viewModel.getEpisodeById(args.id)
    }

    override fun setupObserves() = with(binding) {
        viewModel.episodeDetailState.observe(viewLifecycleOwner, {
            when (it) {
                is UIState.Loading -> {
                }
                is UIState.Error -> {
                }
                is UIState.Success -> {
                    it.data.apply {
                        detailName.text = name
                        detailAirDate.text = airDate
                        detailCreated.text = toFormatDate(created)
                        detailEpisode.text = episode
                            ?.replace("S", "Season ")
                            ?.replace("E", " Episode ")
                    }
                }
            }
        })
    }
}