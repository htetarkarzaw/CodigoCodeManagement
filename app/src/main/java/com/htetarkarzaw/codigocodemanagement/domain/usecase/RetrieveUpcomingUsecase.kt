package com.htetarkarzaw.codigocodemanagement.domain.usecase

import com.htetarkarzaw.codigocodemanagement.domain.repository.MovieRepository
import com.htetarkarzaw.codigocodemanagement.domain.vo.MovieVo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrieveUpcomingUsecase @Inject constructor(private val repo: MovieRepository) {
    suspend operator fun invoke(): Flow<List<MovieVo>> {
        return repo.retrieveUpcomingMovies()
    }
}