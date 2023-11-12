package com.htetarkarzaw.codigocodemanagement.di

import com.htetarkarzaw.codigocodemanagement.data.local.datasource.MovieCacheDataSource
import com.htetarkarzaw.codigocodemanagement.data.remote.datasource.MovieNetworkDataSource
import com.htetarkarzaw.codigocodemanagement.data.repository.MovieRepositoryImpl
import com.htetarkarzaw.codigocodemanagement.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieNetworkDataSource: MovieNetworkDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(
            movieNetworkDataSource = movieNetworkDataSource,
            movieCacheDataSource = movieCacheDataSource
        )
    }
}