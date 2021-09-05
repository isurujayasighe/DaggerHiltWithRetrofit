package com.coding.isuru

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KotlinApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}