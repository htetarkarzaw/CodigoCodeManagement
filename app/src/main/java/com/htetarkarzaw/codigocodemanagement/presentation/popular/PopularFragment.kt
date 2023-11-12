package com.htetarkarzaw.codigocodemanagement.presentation.popular

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.htetarkarzaw.codigocodemanagement.data.Resource
import com.htetarkarzaw.codigocodemanagement.databinding.FragmentPopularBinding
import com.htetarkarzaw.codigocodemanagement.presentation.adapter.MovieAdapter
import com.htetarkarzaw.hanatest.persentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class PopularFragment : BaseFragment<FragmentPopularBinding>(FragmentPopularBinding::inflate) {
    private val viewModel: PopularViewModel by viewModels()
    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter({ clickDetail(it) }, { clickFav(it) })
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dbMovies.collectLatest {
                it.let { movieList ->
                    Timber.e("MovieList: ${movieList.size}")
                    movieList.let {
                        if (movieList.isNotEmpty()) {
                            movieAdapter.submitList(movieList)
                            binding.tvNoData.visibility = View.GONE
                        } else {
                            binding.tvNoData.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movies.collectLatest {
                binding.swipeRefresh.isRefreshing = false
                binding.progressBar.visibility = View.GONE
                when (it) {
                    is Resource.Error -> {
                        Timber.e("Popular List Api Call-> Error ${it.message} ")
                    }

                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        Timber.e("Popular List Api Call-> Loading ")
                    }

                    is Resource.Success -> {
                    }

                    is Resource.Nothing -> {
                        //do nothing
                    }
                }
            }
        }
    }

    override fun initUi() {
        setupRecyclerView()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchPopularMovies()
        }
    }

    private fun setupRecyclerView() {
        binding.rvMovie.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        binding.rvMovie.adapter = movieAdapter
    }

    private fun clickDetail(position: Int) {
        val item = movieAdapter.getClickItem(position)
        val navigation =
            PopularFragmentDirections.actionNavigationPopularToMovieDetailFragment(item.id)
        findNavController().navigate(navigation)
    }

    private fun clickFav(position: Int) {
        val item = movieAdapter.getClickFav(position)
        movieAdapter.notifyItemChanged(position)
        viewModel.toggleFav(movieId = item.id)
    }
}