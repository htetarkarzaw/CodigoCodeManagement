package com.htetarkarzaw.codigocodemanagement.domain.repository

import com.htetarkarzaw.codigocodemanagement.data.Resource
import com.htetarkarzaw.codigocodemanagement.data.local.entities.Movie
import com.htetarkarzaw.codigocodemanagement.domain.vo.MovieVo
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun fetchPopularMovies(): Flow<Resource<Unit>>
    suspend fun fetchUpcomingMovies(): Flow<Resource<Unit>>

    suspend fun toggleFavMovie(movieId: Long)
    suspend fun getMovieById(movieId: Long): Flow<MovieVo>
    suspend fun retrievePopularMovies(): Flow<List<MovieVo>>
    suspend fun retrieveUpcomingMovies(): Flow<List<MovieVo>>
    suspend fun insertMovies(movies: List<Movie>)
}