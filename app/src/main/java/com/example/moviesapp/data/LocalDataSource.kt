package com.example.moviesapp.data

import com.example.moviesapp.data.api.safeCall
import com.example.moviesapp.data.database.MovieDatabase
import com.example.moviesapp.data.model.Movie
import java.lang.Exception

class LocalDataSource(private val db: MovieDatabase) : IMutableDataSource {
    override suspend fun removeAll(): Result<Boolean> {
        return safeCall {
            db.movieDao().deleteAll()
            return@safeCall Result.Success(true)
        }
    }

    override suspend fun insertAll(list: List<Movie>): Result<Boolean> {
        return safeCall {
            db.movieDao().insertAll(list)
            return@safeCall Result.Success(true)
        }
    }

    override suspend fun loadNowPlaying(): Result<List<Movie>> {
        return safeCall {
            val result = db.movieDao().getAll()
            return@safeCall if (result.isEmpty())
                Result.Error(Exception("No item found"))
            else
                Result.Success(result)
        }
    }

}