package com.android.company.app.e_commerce.ui

import android.app.Application
import android.content.Context
import com.android.company.app.e_commerce.BuildConfig
import com.android.company.app.e_commerce.di.getModules
import com.android.company.app.e_commerce.data.remote.Network
import com.android.company.app.e_commerce.utlities.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp  : Application()  {

        override fun onCreate() {
            super.onCreate()
            Network.init(Constants.BASE_URL, BuildConfig.DEBUG)

            startKoin {
                this@MyApp
                androidContext(this@MyApp)
                modules(*getModules())
            }


        }


        init {
            instance = this
        }

        companion object {
            private var instance: MyApp? = null

            fun applicationContext() : Context {
                return instance!!.applicationContext
            }
        }

}