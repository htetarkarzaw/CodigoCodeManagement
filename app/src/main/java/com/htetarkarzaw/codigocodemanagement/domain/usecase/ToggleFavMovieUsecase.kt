package com.htetarkarzaw.codigocodemanagement.domain.usecase

import com.htetarkarzaw.codigocodemanagement.domain.repository.MovieRepository
import javax.inject.Inject

class ToggleFavMovieUsecase @Inject constructor(private val repo: MovieRepository) {
    suspend operator fun invoke(movieId: Long) {
        return repo.toggleFavMovie(movieId = movieId)
    }
}