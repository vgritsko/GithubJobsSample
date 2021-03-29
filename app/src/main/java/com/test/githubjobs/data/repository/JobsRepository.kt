package com.test.githubjobs.data.repository


import com.test.githubjobs.data.model.GithubJob
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface JobsRepository{
    fun getAllJobs(): Flow<Resource<List<GithubJob>>>
    fun getJobById(jobId: String): Flow<GithubJob>
}