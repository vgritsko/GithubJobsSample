package com.test.githubjobs.data.remote

import com.test.githubjobs.data.model.GithubJob
import retrofit2.Response
import retrofit2.http.GET

interface GithubJobsService {
    @GET("positions.json?description=android")
    suspend fun getGithubJobs(): Response<List<GithubJob>>

    companion object {
        const val ENDPOINT_API_URL = "https://jobs.github.com/"
    }
}