package com.afterapps.hephaestus.worker

import android.content.Context
import androidx.work.*
import com.afterapps.hephaestus.repository.RijksRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.concurrent.TimeUnit

class UpdateArtEntriesWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters), KoinComponent {

    private val rijksRepository: RijksRepository by inject()

    companion object {
        fun enqueueUpdateArtEntriesWorker(context: Context) {
            val constraints = Constraints.Builder().apply {
                setRequiresCharging(true)
                setRequiredNetworkType(NetworkType.CONNECTED)
            }.build()

            val workRequest =
                PeriodicWorkRequestBuilder<UpdateArtEntriesWorker>(1, TimeUnit.DAYS).apply {
                    setConstraints(constraints)
                }.build()

            WorkManager.getInstance(context).enqueue(workRequest)
        }
    }

    override suspend fun doWork(): Result {
        return try {
            rijksRepository.updateArtEntries()
            Result.success()
        } catch (error: Throwable) {
            Result.failure()
        }
    }

}