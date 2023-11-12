package com.htetarkarzaw.codigocodemanagement.data.remote

import com.htetarkarzaw.codigocodemanagement.BuildConfig
import com.htetarkarzaw.codigocodemanagement.data.remote.dto.MovieListDTO
import com.htetarkarzaw.codigocodemanagement.utils.Endpoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET(Endpoint.MOVIES_POPULAR)
    suspend fun fetchPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<MovieListDTO>

    @GET(Endpoint.MOVIES_UPCOMING)
    suspend fun fetchUpcomingMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<MovieListDTO>
}