package com.test.githubjobs.data.repository

import kotlinx.coroutines.flow.*
import retrofit2.Response

abstract class BoundRepository<RESULT, REQUEST> {

    protected abstract suspend fun saveRemoteData(response: REQUEST)

    protected abstract fun fetchFromLocal(): Flow<RESULT>

    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>

    fun toFlow() = flow<Resource<RESULT>> {


        emit(Resource.Success(fetchFromLocal().first()))

        val apiResponse = fetchFromRemote()

        val remoteJobs = apiResponse.body()

        if (apiResponse.isSuccessful && remoteJobs != null) {
            saveRemoteData(remoteJobs)
        } else {
            emit(Resource.Failed(apiResponse.message()))
        }

        emitAll(
            fetchFromLocal().map {
                Resource.Success<RESULT>(it)
            }
        )
    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Failed("Error fetching!"))
    }
}