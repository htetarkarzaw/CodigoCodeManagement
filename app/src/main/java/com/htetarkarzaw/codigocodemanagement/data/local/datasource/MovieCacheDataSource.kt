package com.htetarkarzaw.codigocodemanagement.data.local.datasource

import com.htetarkarzaw.codigocodemanagement.data.local.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieCacheDataSource {
    suspend fun toggleFavMovie(movieId: Long)
    suspend fun getMovieById(movieId: Long): Flow<Movie>
    suspend fun retrievePopularMovies(): Flow<List<Movie>>
    suspend fun retrieveUpcomingMovies(): Flow<List<Movie>>
    suspend fun insertMovies(movies: List<Movie>)
}