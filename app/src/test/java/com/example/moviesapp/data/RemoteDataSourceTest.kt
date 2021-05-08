package com.example.moviesapp.data

import com.example.moviesapp.data.api.MovieService
import com.example.moviesapp.data.model.Movies
import com.example.news.helper.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.internal.http.RealResponseBody
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import retrofit2.Response
import java.util.*
@ExperimentalCoroutinesApi

class RemoteDataSourceTest {
    private val service = mockk<MovieService>(relaxed = true)
    private val remoteDataSource = RemoteDataSource(service)

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Test
    fun testSuccessState() {
        coEvery { service.getNowPlaying(1) } returns Response.success(
            Movies(1,Collections.emptyList(),1,1 )
        )
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val result: Result<Movies> = remoteDataSource.loadNowPlaying(1)
            Assertions.assertEquals(true, result is Result.Success)
        }
    }

    @Test
    fun testFailureState() {
        coEvery { service.getNowPlaying(1) } returns Response.error(
            500, RealResponseBody("", 10, null)
        )
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val result: Result<Movies> = remoteDataSource.loadNowPlaying(1)
            Assertions.assertEquals(true, result is Result.Error)
        }
    }
}