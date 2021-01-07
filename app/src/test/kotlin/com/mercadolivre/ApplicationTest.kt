package com.mercadolivre

import android.app.Application

class ApplicationTest : Application() {

    override fun onCreate() {
        super.onCreate()

        setTheme(R.style.AppTheme)
    }
}