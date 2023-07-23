package com.kanchanpal.newsfeed

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApp : Application(){
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

    }
}
