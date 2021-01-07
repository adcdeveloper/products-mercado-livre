package com.mercadolivre

import android.app.Application
import com.mercadolivre.di.ApplicationModule

class MercadoLivreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ApplicationModule(this).start()
    }
}