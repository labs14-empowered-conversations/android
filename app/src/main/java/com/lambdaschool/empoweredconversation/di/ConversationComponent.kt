package com.lambdaschool.empoweredconversation.di

import com.lambdaschool.empoweredconversation.fragment.ConversationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ConversationRepoModule::class])
interface ConversationComponent {
    fun inject(conversationFragment: ConversationFragment)

}