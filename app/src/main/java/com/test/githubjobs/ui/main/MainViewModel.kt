package com.test.githubjobs.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.githubjobs.data.model.GithubJob
import com.test.githubjobs.data.model.State
import com.test.githubjobs.data.repository.JobsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val jobsRepository: JobsRepository) :
    ViewModel() {

    private val _jobsLiveData = MutableLiveData<State<List<GithubJob>>>()
    val jobsLiveData: LiveData<State<List<GithubJob>>> = _jobsLiveData


    fun getAllJobs() {
        viewModelScope.launch {
            jobsRepository.getAllJobs()
                .onStart { _jobsLiveData.value = State.loading() }
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _jobsLiveData.value = state }
        }
    }
}
