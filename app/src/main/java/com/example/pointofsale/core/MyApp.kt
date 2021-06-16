package com.example.pointofsale.core

import android.app.Application
import com.example.pointofsale.BuildConfig
import com.example.pointofsale.di.presentationModule
import com.example.pointofsale.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.d("MyApp_TAG: onCreate:")
        }

        Timber.d("MyApp_TAG: onCreate: ")

        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR)
            androidContext(this@MyApp)
            modules(repositoryModule + presentationModule)
        }
    }
}