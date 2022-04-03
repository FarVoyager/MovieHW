package com.example.moviehw.data.retrofit

import com.example.moviehw.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object ApiInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .header("api_key", BuildConfig.API_KEY)
                .build()
        )
    }
}