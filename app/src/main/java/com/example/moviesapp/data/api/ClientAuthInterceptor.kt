package com.example.moviesapp.data.api

import android.content.Context
import com.example.moviesapp.R
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ClientAuthInterceptor @Inject constructor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", context.getString(R.string.api_key)).build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)

    }
}
