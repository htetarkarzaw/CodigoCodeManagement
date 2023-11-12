package com.htetarkarzaw.codigocodemanagement.presentation.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htetarkarzaw.codigocodemanagement.data.Resource
import com.htetarkarzaw.codigocodemanagement.domain.usecase.FetchPopularUsecase
import com.htetarkarzaw.codigocodemanagement.domain.usecase.RetrievePopularUsecase
import com.htetarkarzaw.codigocodemanagement.domain.usecase.ToggleFavMovieUsecase
import com.htetarkarzaw.codigocodemanagement.domain.vo.MovieVo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val retrievePopularUsecase: RetrievePopularUsecase,
    private val fetchPopularUsecase: FetchPopularUsecase,
    private val toggleFavMovieUsecase: ToggleFavMovieUsecase
) : ViewModel() {
    private val _dbMovies = MutableStateFlow<List<MovieVo>>(emptyList())
    val dbMovies get() = _dbMovies.asStateFlow()

    private val _movies = MutableStateFlow<Resource<Unit>>(Resource.Loading())
    val movies get() = _movies.asStateFlow()

    init {
        fetchPopularMovies()
        viewModelScope.launch {
            retrievePopularUsecase().collectLatest {
                _dbMovies.value = it
            }
        }
    }

    fun fetchPopularMovies() {
        _movies.value = Resource.Loading()
        viewModelScope.launch {
            fetchPopularUsecase().collectLatest {
                _movies.value = it
            }
        }
    }

    fun toggleFav(movieId: Long) {
        viewModelScope.launch {
            toggleFavMovieUsecase(movieId)
        }
    }
}