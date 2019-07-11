package com.lambdaschool.empoweredconversation.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private var retrofit: Retrofit? = null
        private const val BASE_URL = "https://empoweredconversation.herokuapp.com/"

        fun getService(): ECApiService? {
            if (retrofit == null) {
                retrofit = Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit?.create(ECApiService::class.java)
        }
    }
}