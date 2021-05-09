package com.example.moviesapp.domain

import com.example.moviesapp.data.LocalDataSource
import com.example.moviesapp.data.RemoteDataSource
import com.example.moviesapp.data.Result
import com.example.moviesapp.data.model.MovieCast
import com.example.moviesapp.data.model.Movies

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun loadNowPlaying(page: Int): Result<Movies> {
        return when (val result = remoteDataSource.loadNowPlaying(page)) {
            is Result.Success -> {
                if (page == 1) {
                    //remove all on first load
                    localDataSource.removeAll()
                }
                localDataSource.insertAll(result.data.movies)
                result
            }
            is Result.Error -> {
                //page ==1 return from local data source
                if (page != 1) {
                    return Result.Error(result.exception)
                }
                when (val offlineResult = localDataSource.loadNowPlaying()) {
                    is Result.Success -> {
                        Result.Success(Movies(1, offlineResult.data, 1, 1))
                    }
                    is Result.Error -> Result.Error(offlineResult.exception)
                }
            }
        }
    }

    suspend fun loadMovieCast(movieId: Int): Result<MovieCast> =
        remoteDataSource.loadMovieCast(movieId)
}