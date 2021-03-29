package com.test.githubjobs.ui.main.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.test.githubjobs.data.model.GithubJob
import com.test.githubjobs.databinding.ItemJobBinding

class JobViewHolder(private val binding: ItemJobBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(job: GithubJob, onItemClicked: (GithubJob) -> Unit) {
        binding.jobTitle.text = job.title
        binding.jobCompany.text = job.company
        binding.jobLocation.text = job.location
        binding.imageView.load(job.company_logo)

        binding.root.setOnClickListener {
            onItemClicked(job)
        }
    }
}