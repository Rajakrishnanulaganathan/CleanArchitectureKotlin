package com.rk.cleanarchitecturekotlin.di.module

import android.app.Application
import androidx.annotation.Nullable
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rk.cleanarchitecturekotlin.data.remote.api.ApiService
import com.rk.cleanarchitecturekotlin.data.remote.interceptor.RequestInterceptor
import com.rk.cleanarchitecturekotlin.data.remote.interceptor.NetworkInterceptor
import com.rk.cleanarchitecturekotlin.utils.Constatnts
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideCache(application: Application) = Cache(File(application.getCacheDir(), "http-cache"),10 * 1024 * 1024)

    @Provides
    @Singleton
    fun provideNetworkInterceptor(app:Application): NetworkInterceptor = NetworkInterceptor(app.applicationContext)

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache,networkInterceptor: NetworkInterceptor):OkHttpClient{
        val logging=HttpLoggingInterceptor()
        logging.level=HttpLoggingInterceptor.Level.BODY
        val httpClient=OkHttpClient.Builder()
        httpClient.cache(cache)
        httpClient.addInterceptor(networkInterceptor)
        httpClient.addInterceptor(logging)
        httpClient.addNetworkInterceptor(RequestInterceptor())
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson:Gson,okHttpClient:OkHttpClient):ApiService = Retrofit.Builder().baseUrl(
        Constatnts.ENDPOINT).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build().create(ApiService::class.java)


}