package com.example.rickandmortyapp.di

import com.example.rickandmortyapp.common.constants.Constants
import com.example.rickandmortyapp.data.network.apiservices.CharactersApiService
import com.example.rickandmortyapp.data.network.apiservices.EpisodesApiService
import com.example.rickandmortyapp.data.network.apiservices.LocationsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun createOkHttpClient() =
        OkHttpClient().newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun createRetrofitClient(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideCharactersApiService(retrofit: Retrofit): CharactersApiService =
        retrofit.create(CharactersApiService::class.java)

    @Singleton
    @Provides
    fun provideLocationsApiService(retrofit: Retrofit): LocationsApiService =
        retrofit.create(LocationsApiService::class.java)

    @Singleton
    @Provides
    fun provideEpisodesApiService(retrofit: Retrofit): EpisodesApiService =
        retrofit.create(EpisodesApiService::class.java)
}