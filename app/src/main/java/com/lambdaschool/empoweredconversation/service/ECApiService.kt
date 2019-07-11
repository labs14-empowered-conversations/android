package com.lambdaschool.empoweredconversation.service

import com.lambdaschool.empoweredconversation.Token
import io.reactivex.Single
import retrofit2.http.*

interface ECApiService {

    @FormUrlEncoded
    @POST("/oauth/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getToken(
        @Field("grant_type") type: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Header("Authorization") header: String
    ): Single<Token>

}