package com.test.githubjobs.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.githubjobs.data.model.GithubJob
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubJobsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJobs(jobs: List<GithubJob>)

    @Query("DELETE FROM ${GithubJob.TABLE_NAME}")
    suspend fun deleteAllJobs()


    @Query("SELECT * FROM ${GithubJob.TABLE_NAME}")
    fun getAllJobs(): Flow<List<GithubJob>>

    @Query("SELECT * FROM ${GithubJob.TABLE_NAME} WHERE ID = :jobId")
    fun getJobById(jobId: String): Flow<GithubJob>
}