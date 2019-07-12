package com.lambdaschool.empoweredconversation.service

import com.lambdaschool.empoweredconversation.Token
import com.lambdaschool.empoweredconversation.User
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface ECApiService {

    @FormUrlEncoded
    @POST("/oauth/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getToken(
        @Field("grant_type") type: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Header("Authorization") header: String): Single<Token>

    @POST("/createnewuser")
    fun createUser(
        @Body user: User): Call<Unit?>

    @GET("/users/users")
    fun getAllUsers(
        @Header("Authorization") token: String): Observable<MutableList<User>>
}