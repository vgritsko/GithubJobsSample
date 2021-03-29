package com.test.githubjobs.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.githubjobs.data.local.dao.GithubJobsDao
import com.test.githubjobs.data.model.GithubJob


@Database(
    entities = [GithubJob::class],
    version = 1
)
abstract class GithubJobsDatabase : RoomDatabase() {
    abstract fun getJobsDao(): GithubJobsDao

    companion object {

        @Volatile
        private var DATABASE_INSTANCE: GithubJobsDatabase? = null

        fun getDatabase(context: Context): GithubJobsDatabase {
            val tempInstance = DATABASE_INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GithubJobsDatabase::class.java,
                    DB_NAME
                )
                    .build()

                DATABASE_INSTANCE = instance
                return instance
            }
        }

        const val DB_NAME = "github_jobs_database"
    }
}