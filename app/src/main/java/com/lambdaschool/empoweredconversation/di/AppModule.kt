package com.lambdaschool.empoweredconversation.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var application: Application) {

    @Provides
    @Singleton
    fun applicationProvider(): Application {
        return application
    }
}