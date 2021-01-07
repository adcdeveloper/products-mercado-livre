package com.mercadolivre.di

import com.mercadolivre.ApplicationTest
import com.mercadolivre.data.repository.remote.RemoteRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = ApplicationTest::class)
class ApplicationModuleTest : AutoCloseKoinTest() {

    private lateinit var applicationModule: ApplicationModule

    @Before
    fun setup() {
        applicationModule = ApplicationModule(RuntimeEnvironment.systemContext)
    }

    @Test
    fun `should create instance of RemoteRepository when performed start`() {
        applicationModule.start()

        val remoteRepository: RemoteRepository = get()

        assertNotNull(remoteRepository)
    }

}