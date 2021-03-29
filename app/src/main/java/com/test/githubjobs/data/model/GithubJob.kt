package com.test.githubjobs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.githubjobs.data.model.GithubJob.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class GithubJob(

    @PrimaryKey
    var id: String = "",
    var type: String? = null,
    var url: String? = null,
    var company: String? = null,
    var title: String? = null,
    var description: String? = null,
    var location: String? = null,
    var company_logo: String? = null
) {
    companion object {
        const val TABLE_NAME = "jobs"
    }
}


