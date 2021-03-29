package com.test.githubjobs.di.module

import android.app.Application
import com.test.githubjobs.data.local.GithubJobsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class GithubJobsDatabaseModule{

@Singleton
@Provides
fun provideDatabase(application: Application) = GithubJobsDatabase.getDatabase(application)

@Singleton
@Provides
fun provideJobsDao(database: GithubJobsDatabase) = database.getJobsDao()
}