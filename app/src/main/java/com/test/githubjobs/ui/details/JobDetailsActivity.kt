package com.test.githubjobs.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import com.test.githubjobs.R
import com.test.githubjobs.databinding.ActivityJobDetailsBinding
import com.test.githubjobs.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val JOB_ID = "jobId"

@AndroidEntryPoint
class JobDetailsActivity : BaseActivity<JobDetailsViewModel, ActivityJobDetailsBinding>() {

    @Inject
    lateinit var viewModelFactory: JobDetailsViewModel.JobDetailsViewModelFactory

    override val viewModel: JobDetailsViewModel by viewModels {
        val jobId = intent.extras?.getString(JOB_ID)
            ?: throw IllegalArgumentException()

        JobDetailsViewModel.provideFactory(viewModelFactory, jobId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }


    private fun initUI() {
        viewModel.job.observe(this) { job ->
            binding.apply {
                jobTitle.text = job.title
                jobCompany.text = job.company
                jobDescription.text = job.description
                imageView.load(job.company_logo)
            }
        }
    }

    override fun getViewBinding(): ActivityJobDetailsBinding =
        ActivityJobDetailsBinding.inflate(layoutInflater)
}