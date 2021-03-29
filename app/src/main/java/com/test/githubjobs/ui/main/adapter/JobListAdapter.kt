package com.test.githubjobs.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.test.githubjobs.data.model.GithubJob
import com.test.githubjobs.databinding.ItemJobBinding
import com.test.githubjobs.ui.main.viewholder.JobViewHolder

class JobListAdapter(
    private val onItemClicked: (GithubJob) -> Unit
) : ListAdapter<GithubJob, JobViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = JobViewHolder(
        ItemJobBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GithubJob>() {
            override fun areItemsTheSame(oldItem: GithubJob, newItem: GithubJob): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GithubJob, newItem: GithubJob): Boolean =
                oldItem == newItem
        }
    }
}
