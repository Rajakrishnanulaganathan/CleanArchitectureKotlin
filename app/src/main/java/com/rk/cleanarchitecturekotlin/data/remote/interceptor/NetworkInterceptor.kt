package com.rk.cleanarchitecturekotlin.data.remote.interceptor

import android.content.Context
import com.rk.cleanarchitecturekotlin.utils.NetworkUtility
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor(private val context: Context) :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            request.newBuilder()
                .header("Cache-Control", "public, max-age=" + 60)
                .build()

        return chain.proceed(request)
    }

}
