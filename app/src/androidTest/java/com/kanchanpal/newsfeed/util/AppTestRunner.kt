package com.kanchanpal.newsfeed.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.kanchanpal.newsfeed.NewsTestApp

/**
 * Custom runner to disable dependency injection.
 */
class AppTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, NewsTestApp::class.java.name, context)
    }
}
