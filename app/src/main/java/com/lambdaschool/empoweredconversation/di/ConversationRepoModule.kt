package com.lambdaschool.empoweredconversation.di

import android.app.Application
import com.lambdaschool.empoweredconversation.repository.ConversationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ConversationRepoModule {

    @Provides
    @Singleton
    fun conversationRepoProvider(application: Application): ConversationRepository {
        return ConversationRepository(application)
    }
}