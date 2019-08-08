package com.lambdaschool.empoweredconversation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lambdaschool.empoweredconversation.repository.ConversationRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ConversationViewModelFactory @Inject constructor(var conversationRepository: ConversationRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConversationViewModel(conversationRepository) as T
    }

}