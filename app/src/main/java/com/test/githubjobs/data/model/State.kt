package com.test.githubjobs.data.model

import com.test.githubjobs.data.repository.Resource

sealed class State<T> {
    class Loading<T> : State<T>()

    data class Success<T>(val data: T) : State<T>()

    data class Error<T>(val e: Throwable) : State<T>()

    fun isLoading(): Boolean = this is Loading

    fun isSuccessful(): Boolean = this is Success

    fun isFailed(): Boolean = this is Error

    companion object {

        fun <T> loading() = Loading<T>()


        fun <T> success(data: T) = Success(data)


        fun <T> error(error: Throwable) = Error<T>(error)


        fun <T> fromResource(resource: Resource<T>): State<T> = when (resource) {
            is Resource.Success -> success(resource.data)
            is Resource.Failed -> error(resource.message)
        }
    }
}