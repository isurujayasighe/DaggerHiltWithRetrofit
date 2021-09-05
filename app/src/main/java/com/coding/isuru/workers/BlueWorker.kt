package com.coding.isuru.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.coding.isuru.R
import dagger.hilt.android.qualifiers.ApplicationContext

class BlueWorker(mContext: Context, workerParameters: WorkerParameters): Worker(mContext,workerParameters){

    override fun doWork(): Result {

        val appContext = applicationContext

        makeStatusNotification("Blurring image",appContext)

        return try {
            val picture = BitmapFactory.decodeResource(
                appContext.resources, R.drawable.android_cupcake)

            val output = blurBitmap(picture,appContext)

            val outputUri = writeBitmapToFile(appContext,output)

            makeStatusNotification("Output is $outputUri", appContext)

            Result.success()

        } catch (throwable: Throwable){
            Log.d("BlueWorker", throwable.localizedMessage)
            Result.failure()

        }
    }
}