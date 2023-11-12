package com.htetarkarzaw.codigocodemanagement.data.repository

import com.htetarkarzaw.codigocodemanagement.data.Resource
import com.htetarkarzaw.codigocodemanagement.data.local.datasource.MovieCacheDataSource
import com.htetarkarzaw.codigocodemanagement.data.local.entities.Movie
import com.htetarkarzaw.codigocodemanagement.data.remote.RemoteResource
import com.htetarkarzaw.codigocodemanagement.data.remote.datasource.MovieNetworkDataSource
import com.htetarkarzaw.codigocodemanagement.domain.repository.MovieRepository
import com.htetarkarzaw.codigocodemanagement.domain.vo.MovieVo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieNetworkDataSource: MovieNetworkDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override suspend fun fetchPopularMovies(): Flow<Resource<Unit>> = flow {
        when (val response = movieNetworkDataSource.fetchPopularMovies()) {
            is RemoteResource.ErrorEvent -> {
                emit(Resource.Error("Retrieve Popular Movie Error!${response.getErrorMessage()}"))
            }

            is RemoteResource.LoadingEvent -> {
                emit(Resource.Loading())
            }

            is RemoteResource.SuccessEvent -> {
                response.data?.results?.let {
                    insertMovies(movies = it.map { dto ->
                        dto.toEntity("popular")
                    })
                }
                emit(Resource.Success(Unit))
            }
        }

    }

    override suspend fun fetchUpcomingMovies(): Flow<Resource<Unit>> = flow {
        when (val response = movieNetworkDataSource.fetchUpcomingMovies()) {
            is RemoteResource.ErrorEvent -> {
                emit(Resource.Error("Retrieve Upcoming Movie Error!${response.getErrorMessage()}"))
            }

            is RemoteResource.LoadingEvent -> {
                emit(Resource.Loading())
            }

            is RemoteResource.SuccessEvent -> {
                response.data?.results?.let {
                    insertMovies(movies = it.map { dto ->
                        dto.toEntity("upcoming")
                    })
                }
                emit(Resource.Success(Unit))
            }
        }
    }

    override suspend fun toggleFavMovie(movieId: Long) {
        movieCacheDataSource.toggleFavMovie(movieId = movieId)
    }

    override suspend fun getMovieById(movieId: Long): Flow<MovieVo> {
        return movieCacheDataSource.getMovieById(movieId = movieId).map { it.toVo() }
    }

    override suspend fun retrievePopularMovies(): Flow<List<MovieVo>> {
        return movieCacheDataSource.retrievePopularMovies().map { list ->
            list.map { it.toVo() }
        }
    }

    override suspend fun retrieveUpcomingMovies(): Flow<List<MovieVo>> {
        return movieCacheDataSource.retrieveUpcomingMovies().map { list ->
            list.map { it.toVo() }
        }
    }

    override suspend fun insertMovies(movies: List<Movie>) {
        return movieCacheDataSource.insertMovies(movies = movies)
    }
}