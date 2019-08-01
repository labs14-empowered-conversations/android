package com.lambdaschool.empoweredconversation.service

import com.google.gson.JsonElement
import com.lambdaschool.empoweredconversation.model.Conversation
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ConversationService {
    @POST("/conversations")
    fun postConversation(@Body conversation: Conversation): Call<Conversation>
}