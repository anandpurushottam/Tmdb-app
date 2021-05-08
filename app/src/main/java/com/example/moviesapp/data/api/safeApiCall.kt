package com.example.moviesapp.data.api
import java.io.IOException
import com.example.moviesapp.data.Result

suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String="Something went wrong"): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        Result.Error(IOException(errorMessage, e))
    }
}
