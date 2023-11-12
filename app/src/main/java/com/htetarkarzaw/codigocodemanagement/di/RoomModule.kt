package com.htetarkarzaw.codigocodemanagement.di

import android.content.Context
import androidx.room.Room
import com.htetarkarzaw.codigocodemanagement.data.local.dao.MovieDao
import com.htetarkarzaw.codigocodemanagement.data.local.datasource.MovieCacheDataSource
import com.htetarkarzaw.codigocodemanagement.data.local.datasource.MovieCacheDataSourceImpl
import com.htetarkarzaw.codigocodemanagement.utils.Constant
import com.htetarkarzaw.codigocodemanagement.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideMovieCacheDataSource(dao: MovieDao): MovieCacheDataSource {
        return MovieCacheDataSourceImpl(movieDao = dao)
    }

    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDatabase): MovieDao {
        return db.movieDao()
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java, Constant.MOVIE_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }


}