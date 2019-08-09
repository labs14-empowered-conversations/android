package com.lambdaschool.empoweredconversation

import android.app.Application
import com.lambdaschool.empoweredconversation.di.AppModule
import com.lambdaschool.empoweredconversation.di.ConversationComponent
import com.lambdaschool.empoweredconversation.di.DaggerConversationComponent

class App : Application() {
    lateinit var conversationComponent: ConversationComponent

    companion object {
        lateinit var app: App
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        conversationComponent = DaggerConversationComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}