package com.example.weatherapp.di

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.source.network.ApiConstants
import com.example.weatherapp.source.network.WeatherApiService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
    @IntoSet
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        val level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
        return HttpLoggingInterceptor()
            .setLevel(level)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    @IntoSet
    @Provides
    @Singleton
    fun provideRxCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @IntoSet
    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideWeatherApiService(
        interceptors: @JvmSuppressWildcards Set<Interceptor>,
        converters: @JvmSuppressWildcards Set<Converter.Factory>,
        callAdapters: @JvmSuppressWildcards Set<CallAdapter.Factory>
    ): WeatherApiService {
        val okHttpClient = createHttpClient(interceptors)
        return createRetrofit(
            ApiConstants.BASE_URL,
            okHttpClient,
            converters,
            callAdapters
        ).create(WeatherApiService::class.java)
    }

    private fun createHttpClient(interceptors: Set<Interceptor>): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        interceptors.forEach { builder.addInterceptor(it) }
        return builder.build()
    }

    private fun createRetrofit(
        baseUrl: String,
        client: OkHttpClient,
        converters: Set<Converter.Factory>,
        callAdapters: Set<CallAdapter.Factory>
    ): Retrofit {
        val builder = Retrofit.Builder()
        converters.forEach { builder.addConverterFactory(it) }
        callAdapters.forEach { builder.addCallAdapterFactory(it) }
        builder.client(client)
        builder.baseUrl(baseUrl)
        return builder.build()
    }
}