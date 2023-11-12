package com.htetarkarzaw.codigocodemanagement.domain.usecase

import com.htetarkarzaw.codigocodemanagement.data.Resource
import com.htetarkarzaw.codigocodemanagement.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUpcomingUsecase @Inject constructor(private val repo: MovieRepository) {
    suspend operator fun invoke(): Flow<Resource<Unit>> {
        return repo.fetchUpcomingMovies()
    }
}