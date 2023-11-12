package com.htetarkarzaw.codigocodemanagement.data.local.datasource

import com.htetarkarzaw.codigocodemanagement.data.local.dao.MovieDao
import com.htetarkarzaw.codigocodemanagement.data.local.entities.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieCacheDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieCacheDataSource {
    override suspend fun toggleFavMovie(movieId: Long) {
        val movie = movieDao.getMovieById(movieId)
        if (movie != null) {
            movie.isFav = movie.isFav != true
            movieDao.updateMovie(movie)
        }
    }

    override suspend fun getMovieById(movieId: Long): Flow<Movie> {
        return movieDao.getMovieByIdViaFlow(movieId)
    }

    override suspend fun retrievePopularMovies(): Flow<List<Movie>> {
        return movieDao.retrievesPopularMovies()
    }

    override suspend fun retrieveUpcomingMovies(): Flow<List<Movie>> {
        return movieDao.retrievesUpcomingMovies()
    }

    override suspend fun insertMovies(movies: List<Movie>) {
        return movieDao.insertMovies(movies)
    }
}