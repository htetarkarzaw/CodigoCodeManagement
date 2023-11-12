package com.htetarkarzaw.codigocodemanagement.data.remote.datasource

import com.htetarkarzaw.codigocodemanagement.data.remote.RemoteResource
import com.htetarkarzaw.codigocodemanagement.data.remote.dto.MovieListDTO

interface MovieNetworkDataSource {
    suspend fun fetchPopularMovies(): RemoteResource<MovieListDTO>
    suspend fun fetchUpcomingMovies(): RemoteResource<MovieListDTO>
}