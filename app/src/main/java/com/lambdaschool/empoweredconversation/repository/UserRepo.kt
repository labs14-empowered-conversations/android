package com.lambdaschool.empoweredconversation.repository

import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.lambdaschool.empoweredconversation.Token
import com.lambdaschool.empoweredconversation.User
import com.lambdaschool.empoweredconversation.utils.AuthUtils
import com.lambdaschool.empoweredconversation.service.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepo() {
    private val compositeDisposable = CompositeDisposable()
    val tokenLiveData = MutableLiveData<Token>()
    val registeredBoolean = MutableLiveData<Boolean>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getToken(username: String, password: String): MutableLiveData<Token> {
        val ecApiService = RetrofitInstance.getService()
        val tokenSingle = ecApiService?.getToken(
            "password", username, password, AuthUtils.getBase64ApiCredentials()
        )
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<Token>() {
                override fun onSuccess(t: Token) {
                    tokenLiveData.postValue(t)
                }

                override fun onError(e: Throwable) {
                    val i = 0
                }
            })
        return tokenLiveData
    }

    fun createUser(user: User): MutableLiveData<Boolean> {
        val ecApiService = RetrofitInstance.getService()
        val call = ecApiService?.createUser(user)
        call?.enqueue(object: Callback<Unit?>{
            override fun onFailure(call: Call<Unit?>, t: Throwable) {
                Log.i("ECApiServiceCreateUser", t.localizedMessage)
            }

            override fun onResponse(call: Call<Unit?>, response: Response<Unit?>) {
                registeredBoolean.postValue(true)
                Log.i("ECApiServiceCreateUser", "User registered")
            }
        })
        return registeredBoolean
    }
}