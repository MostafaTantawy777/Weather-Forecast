package com.tantawy.weatherforecast

import android.app.Application
import com.tantawy.data.di.apiModule
import com.tantawy.data.di.remoteModule
import com.tantawy.data.di.repoModule
import com.tantawy.domain.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        application = this
        startKoin {
            androidContext(this@WeatherApplication)
            this.modules(
                listOf(
                    remoteModule,
                    useCasesModule,
                    repoModule,
                    apiModule
                )
            )
        }

        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var appContext: Application
        lateinit var application: WeatherApplication
    }
}