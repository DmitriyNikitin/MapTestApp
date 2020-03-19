package com.example.testmapapp

import android.app.Application


class App : Application() {

    companion object{
        var application: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        application = this

    }
}