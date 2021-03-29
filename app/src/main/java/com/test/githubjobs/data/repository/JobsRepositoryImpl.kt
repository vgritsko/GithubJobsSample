package com.test.githubjobs.data.repository

import com.test.githubjobs.data.local.dao.GithubJobsDao
import com.test.githubjobs.data.model.GithubJob
import com.test.githubjobs.data.remote.GithubJobsService

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class JobsRepositoryImpl @Inject constructor(
    private val jobsRemoteDataSource: GithubJobsService,
    private val jobsLocalDataSource: GithubJobsDao
) : JobsRepository {
    override fun getAllJobs(): Flow<Resource<List<GithubJob>>> {

        return object : BoundRepository<List<GithubJob>, List<GithubJob>>() {

            override suspend fun saveRemoteData(response: List<GithubJob>) =
                jobsLocalDataSource.addJobs(response)

            override fun fetchFromLocal(): Flow<List<GithubJob>> =
                jobsLocalDataSource.getAllJobs()

            override suspend fun fetchFromRemote(): Response<List<GithubJob>> =
                jobsRemoteDataSource.getGithubJobs()
        }.toFlow()
    }

    override fun getJobById(jobId: String): Flow<GithubJob> =
        jobsLocalDataSource.getJobById(jobId)

}