package com.htetarkarzaw.codigocodemanagement.presentation.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htetarkarzaw.codigocodemanagement.domain.usecase.GetMovieByIdUsecase
import com.htetarkarzaw.codigocodemanagement.domain.usecase.ToggleFavMovieUsecase
import com.htetarkarzaw.codigocodemanagement.domain.vo.MovieVo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieByIdUsecase: GetMovieByIdUsecase,
    private val toggleFavMovieUsecase: ToggleFavMovieUsecase
) : ViewModel() {
    private val _dbMovies = MutableStateFlow(MovieVo())
    val dbMovies get() = _dbMovies.asStateFlow()

    fun getMovieById(movieId: Long) {
        viewModelScope.launch {
            getMovieByIdUsecase(movieId).collectLatest {
                _dbMovies.value = it
            }
        }
    }

    fun toggleFav(movieId: Long) {
        viewModelScope.launch {
            toggleFavMovieUsecase(movieId)
        }
    }
}