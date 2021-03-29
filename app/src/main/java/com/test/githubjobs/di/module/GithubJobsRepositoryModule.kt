package com.test.githubjobs.di.module

import com.test.githubjobs.data.repository.JobsRepository
import com.test.githubjobs.data.repository.JobsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class GithubJobsRepositoryModule {
    @ActivityRetainedScoped
    @Binds
    abstract fun bindJobRepository(repository: JobsRepositoryImpl):JobsRepository
}