package com.htetarkarzaw.codigocodemanagement.presentation.movie_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.htetarkarzaw.codigocodemanagement.R
import com.htetarkarzaw.codigocodemanagement.databinding.FragmentMovieDetailBinding
import com.htetarkarzaw.codigocodemanagement.presentation.MainActivity
import com.htetarkarzaw.codigocodemanagement.utils.Endpoint
import com.htetarkarzaw.hanatest.persentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(FragmentMovieDetailBinding::inflate) {
    private var movieId: Long = 0L
    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel: MovieDetailViewModel by viewModels()
    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dbMovies.collectLatest { movieVo ->
                if (movieVo.id != -1L) {
                    binding.tvTitle.text = movieVo.title
                    binding.tvDescription.text = movieVo.overview
                    binding.tvAverage.text = movieVo.averageVote.toString()
                    binding.tvReleaseDate.text = movieVo.releasedDate
                    Glide.with(requireContext())
                        .load(Endpoint.MOVIES_IMAGE_URL + (movieVo?.imageUrl ?: ""))
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .placeholder(R.drawable.place_holder)
                        .into(binding.ivMovie)
                    if (movieVo.isFav) {
                        Glide.with(requireContext())
                            .load(R.drawable.ic_fav_select)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .placeholder(R.drawable.place_holder)
                            .into(binding.imgFav)
                    } else {
                        Glide.with(requireContext())
                            .load(R.drawable.ic_fav_unselect)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .placeholder(R.drawable.place_holder)
                            .into(binding.imgFav)
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()
    }

    override fun initUi() {
        movieId = args.movieId
        viewModel.getMovieById(movieId)
        binding.imgFav.setOnClickListener {
            viewModel.toggleFav(movieId)
        }
    }

    private fun setupActionBar() {
        (requireActivity() as MainActivity).supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(false)
        }
    }

}