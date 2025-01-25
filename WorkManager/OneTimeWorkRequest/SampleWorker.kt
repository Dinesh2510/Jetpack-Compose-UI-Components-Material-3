package com.example.composedemo.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SampleWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
        // lets print the current time and date when worker running
        val timestamp = System.currentTimeMillis()

        val currentTime =
            java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault())
                .format(java.util.Date(timestamp))

        Log.d("SAMPLEWORKER", "doWork: $currentTime")

        Thread.sleep(2000)//Simulate a delay
        return Result.success()

    }
}

// todo add below code in to your MainActivity
// Create a OneTimeWorkRequest
val oneTimeWorkRequest = OneTimeWorkRequestBuilder<CounterWorker>().build()

// Enqueue the work
WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)

// Observe the work state
WorkManager.getInstance(this)
.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
.observe(this, Observer { workInfo ->
    if (workInfo != null) {
        when (workInfo.state) {
            WorkInfo.State.ENQUEUED -> Log.d("WorkState", "OneTimeWork is enqueued.")
            WorkInfo.State.RUNNING -> Log.d("WorkState", "OneTimeWork is running.")
            WorkInfo.State.SUCCEEDED -> Log.d("WorkState", "OneTimeWork succeeded.")
            WorkInfo.State.FAILED -> Log.d("WorkState", "OneTimeWork failed.")
            WorkInfo.State.CANCELLED -> Log.d("WorkState", "OneTimeWork was cancelled.")
            WorkInfo.State.BLOCKED -> Log.d("WorkState", "OneTimeWork is blocked.")
        }
    }
})