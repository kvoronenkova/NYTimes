package com.ksu.exercise3

import android.app.Application
import com.ksu.exercise3.di.controllerModule
import com.ksu.exercise3.di.repoModule
import com.ksu.exercise3.di.useCaseModule
import com.ksu.exercise3.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@MyApplication)
            // use modules

        }
        loadKoinModules(listOf(useCaseModule, repoModule, viewModelModule, useCaseModule,  controllerModule))

    }
}