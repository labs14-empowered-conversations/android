package com.lambdaschool.empoweredconversation.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.lambdaschool.empoweredconversation.Token
import com.lambdaschool.empoweredconversation.utils.AuthUtils
import com.lambdaschool.empoweredconversation.service.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class UserRepo() {
    private val compositeDisposable = CompositeDisposable()
    val tokenLiveData = MutableLiveData<Token>()


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
}