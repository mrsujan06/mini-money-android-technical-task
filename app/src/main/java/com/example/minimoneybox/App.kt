package com.example.minimoneybox

import android.app.Application
import com.example.minimoneybox.dagger.AppComponent
import com.example.minimoneybox.dagger.DaggerAppComponent

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }

}