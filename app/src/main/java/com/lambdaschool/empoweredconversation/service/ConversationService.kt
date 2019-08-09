package com.lambdaschool.empoweredconversation.service

import com.lambdaschool.empoweredconversation.model.Conversation
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ConversationService {
    @POST("/conversations")
    fun postConversation(@Body conversation: Conversation): Call<Conversation>
}