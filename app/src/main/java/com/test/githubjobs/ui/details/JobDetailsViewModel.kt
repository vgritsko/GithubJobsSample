package com.test.githubjobs.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.test.githubjobs.data.repository.JobsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class JobDetailsViewModel @AssistedInject constructor(
    jobsRepository: JobsRepository,
    @Assisted jobId: String
) : ViewModel() {

    val job = jobsRepository.getJobById(jobId).asLiveData()

    @AssistedFactory
    interface JobDetailsViewModelFactory {
        fun create(jobId: String): JobDetailsViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: JobDetailsViewModelFactory,
            jobId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(jobId) as T
            }
        }
    }
}