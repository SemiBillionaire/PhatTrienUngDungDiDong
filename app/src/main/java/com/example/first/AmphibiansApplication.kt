package com.example.first

import android.app.Application
import com.example.first.data.AppContainer
import com.example.first.data.DefaultAppContainer

class AmphibiansApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
