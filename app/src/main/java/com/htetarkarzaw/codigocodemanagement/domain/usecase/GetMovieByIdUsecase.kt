package com.htetarkarzaw.codigocodemanagement.domain.usecase

import com.htetarkarzaw.codigocodemanagement.domain.repository.MovieRepository
import com.htetarkarzaw.codigocodemanagement.domain.vo.MovieVo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieByIdUsecase @Inject constructor(private val repo: MovieRepository) {
    suspend operator fun invoke(movieId: Long): Flow<MovieVo> {
        return repo.getMovieById(movieId = movieId)
    }
}