package com.example.callphobia_overs.main.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application  : Application(){

    companion object{
        lateinit var preferManager: SharedPreference
    }
    override fun onCreate() {
        preferManager = SharedPreference(applicationContext)
        super.onCreate()
    }
}