package com.example.moviesapp.data.api
import java.io.IOException
import com.example.moviesapp.data.Result

suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        Result.Error(IOException(e.localizedMessage, e))
    }
}
