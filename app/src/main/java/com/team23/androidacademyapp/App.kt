package com.team23.androidacademyapp

import android.app.Application
import com.team23.androidacademyapp.data.di.data
import com.team23.androidacademyapp.domain.di.domain
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(data, domain))
        }
    }
}