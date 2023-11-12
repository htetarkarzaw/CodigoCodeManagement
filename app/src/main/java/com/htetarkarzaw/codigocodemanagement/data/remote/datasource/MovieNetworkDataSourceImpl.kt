package com.htetarkarzaw.codigocodemanagement.data.remote.datasource

import com.htetarkarzaw.codigocodemanagement.data.remote.MovieApiService
import com.htetarkarzaw.codigocodemanagement.data.remote.RemoteResource
import com.htetarkarzaw.codigocodemanagement.data.remote.dto.MovieDTO
import com.htetarkarzaw.codigocodemanagement.data.remote.dto.MovieListDTO
import com.htetarkarzaw.codigocodemanagement.data.remote.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieNetworkDataSourceImpl @Inject constructor(
    private val apiService: MovieApiService
) : MovieNetworkDataSource {
    override suspend fun fetchPopularMovies(): RemoteResource<MovieListDTO>
        = safeApiCall { apiService.fetchPopularMovies() }


    override suspend fun fetchUpcomingMovies(): RemoteResource<MovieListDTO> =
        safeApiCall { apiService.fetchUpcomingMovie() }
}