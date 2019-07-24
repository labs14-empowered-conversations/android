package com.lambdaschool.empoweredconversation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.empoweredconversation.model.Conversation
import com.lambdaschool.empoweredconversation.repository.ConversationRepository

class ConversationViewModel(private var conversationRepository: ConversationRepository): ViewModel() {

    fun postConversation(conversation: Conversation): LiveData<Conversation> {
        return conversationRepository.postConversation(conversation)
    }
}