package com.mercadolivre.di

import android.content.Context
import com.mercadolivre.data.repository.remote.RemoteRepositoryImpl
import com.mercadolivre.data.repository.remote.RemoteRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class ApplicationModule(private val applicationContext: Context) {

    private val remoteModule = module {
        single<RemoteRepository> { RemoteRepositoryImpl() }
    }

    fun start() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)
            modules(
                arrayListOf(
                    remoteModule
                )
            )
        }
    }
}