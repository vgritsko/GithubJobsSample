package com.test.githubjobs.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.test.githubjobs.R
import com.test.githubjobs.commons.ui.showSnackBar
import com.test.githubjobs.data.model.GithubJob
import com.test.githubjobs.data.model.State
import com.test.githubjobs.databinding.ActivityMainBinding
import com.test.githubjobs.ui.base.BaseActivity
import com.test.githubjobs.ui.details.JOB_ID
import com.test.githubjobs.ui.details.JobDetailsActivity
import com.test.githubjobs.ui.main.adapter.JobListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class MainActivity :
    BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModels()

    private val adapter = JobListAdapter(this::onItemClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        initUI()
    }

    override fun onStart() {
        super.onStart()
        startObserving()
    }

    private fun initUI() {
        binding.run {
            jobsRecyclerView.adapter = adapter
            swipeRefreshLayout.setOnRefreshListener { getJobs() }
        }
        viewModel.jobsLiveData.value?.let { currentState ->
            if (!currentState.isSuccessful()) {
                getJobs()
            }
        }
    }

    private fun startObserving() {
        viewModel.jobsLiveData.observe(this) { state ->
            when (state) {
                is State.Loading -> showLoadingProgress(true)
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        adapter.submitList(state.data.toMutableList())
                        showLoadingProgress(false)
                    }
                }
                is State.Error -> {
                    showSnackBar(binding.root, "Error")
                    showLoadingProgress(false)
                }
            }
        }
    }

    private fun showLoadingProgress(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun getJobs() = viewModel.getAllJobs()

    private fun onItemClicked(job: GithubJob) {
        val intent = Intent(this, JobDetailsActivity::class.java)
            .apply { putExtra(JOB_ID, job.id) }
        startActivity(intent, Bundle())
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}


