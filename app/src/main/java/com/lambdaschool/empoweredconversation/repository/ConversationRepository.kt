package com.lambdaschool.empoweredconversation.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.lambdaschool.empoweredconversation.model.Conversation
import com.lambdaschool.empoweredconversation.service.ConversationService
import com.lambdaschool.empoweredconversation.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConversationRepository(application: Application) {

    private var conversationLiveData = MutableLiveData<Conversation>()
    private var conversationService: ConversationService? = RetrofitInstance.getService()

    fun postConversation(conversation: Conversation): MutableLiveData<Conversation> {
        val call = conversationService?.postConversation(conversation)
        call?.enqueue(object : Callback<Conversation> {
            override fun onFailure(call: Call<Conversation>, t: Throwable) {
                Log.i("Conversation", t.message)
                conversationLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Conversation>, response: Response<Conversation>) {
                conversationLiveData.postValue(response.body())
            }
        })
        return conversationLiveData
    }

}