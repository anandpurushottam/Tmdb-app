package com.example.moviesapp.data

import android.content.Context
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.moviesapp.data.database.MovieDao
import com.example.moviesapp.data.database.MovieDatabase
import com.example.moviesapp.data.model.Movie
import com.example.news.helper.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class LocalDataSourceTest {
    private lateinit var movieDatabase: MovieDatabase
    private lateinit var movieDao: MovieDao


    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        movieDatabase =
            Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        movieDao = movieDatabase.movieDao()
        localDataSource = LocalDataSource(movieDatabase)

        coroutinesTestRule.testDispatcher.runBlockingTest {
            localDataSource.insertAll(
                listOf(
                    getMockedMovie(),
                    getMockedMovie(),
                    getMockedMovie()
                )
            )
        }
    }

    @Test
    fun removeAll() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            when (val result = localDataSource.removeAll()) {
                is Result.Success -> assertEquals(result.data, true)
                is Result.Error -> assertEquals(false, true)
            }
        }
    }


    @Test
    fun loadMovies() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            when (val result: Result<List<Movie>> = localDataSource.loadNowPlaying()) {
                is Result.Success -> assertEquals(result.data.size, 3)
                is Result.Error -> assertEquals(true, false)
            }

        }
    }


    @After
    fun closeDb() {
        movieDatabase.close()
    }

    private fun getMockedMovie(): Movie {
        return Movie(id = System.nanoTime().toInt())
    }
}