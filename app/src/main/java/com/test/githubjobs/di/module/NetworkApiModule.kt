package com.test.githubjobs.di.module


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.githubjobs.data.remote.GithubJobsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkApiModule {
    @Singleton
    @Provides
    fun provideRetrofitService(): GithubJobsService = Retrofit.Builder()
        .baseUrl(GithubJobsService.ENDPOINT_API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(GithubJobsService::class.java)
}
