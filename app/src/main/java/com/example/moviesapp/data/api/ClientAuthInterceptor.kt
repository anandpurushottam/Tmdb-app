package com.example.moviesapp.data.api

import android.content.Context
import com.example.moviesapp.R
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ClientAuthInterceptor @Inject constructor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val url = chain.request().url().newBuilder()
            .addQueryParameter("apiKey", context.getString(R.string.api_key)).build()
        requestBuilder.url(url)
        return chain.proceed(requestBuilder.build())
    }
}
