package com.lambdaschool.empoweredconversation.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private var retrofit: Retrofit? = null
        private const val BASE_URL = "https://empoweredconversation.herokuapp.com/"

        fun getService(): ConversationService? {
            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                retrofit = Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit?.create(ConversationService::class.java)
        }
    }
}