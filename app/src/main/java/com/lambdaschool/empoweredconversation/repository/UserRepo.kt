package com.lambdaschool.empoweredconversation.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.lambdaschool.empoweredconversation.BuildConfig
import com.lambdaschool.empoweredconversation.Token
import com.lambdaschool.empoweredconversation.User
import com.lambdaschool.empoweredconversation.service.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepo() {
    private val compositeDisposable = CompositeDisposable()
    val tokenLiveData = MutableLiveData<Token>()
    val userLiveData = MutableLiveData<MutableList<User>>()
    val registeredBoolean = MutableLiveData<Boolean>()
    val userList = mutableListOf<User>()

    fun getToken(username: String, password: String): MutableLiveData<Token> {
        val ecApiService = RetrofitInstance.getService()
        val result = ecApiService?.getToken(
            "password", username, password, BuildConfig.authCredential
        )
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<Token>() {
                override fun onSuccess(t: Token) {
                    tokenLiveData.postValue(t)
                }

                override fun onError(e: Throwable) {

                }
            })
        return tokenLiveData
    }

    fun createUser(user: User): MutableLiveData<Boolean> {
        val ecApiService = RetrofitInstance.getService()
        val call = ecApiService?.createUser(user)
        call?.enqueue(object : Callback<Unit?> {
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

    fun getAllUsers(token: String): MutableLiveData<MutableList<User>> {
        val ecApiService = RetrofitInstance.getService()
        val result = ecApiService?.getAllUsers(token)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.flatMap {
                Log.i("getUsers", "${it.size}")
                Observable.fromIterable(it)
            }
            ?.subscribeWith(object : Observer<User> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: User) {
                    t.let { userList.add(it) }
                }

                override fun onError(e: Throwable) {
                    Log.i("getUsers", e.localizedMessage)
                }

                override fun onComplete() {
                    userLiveData.postValue(userList)
                }

            })
        return userLiveData
    }
}