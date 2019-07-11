package com.lambdaschool.empoweredconversation.repository

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.lambdaschool.empoweredconversation.Token
import com.lambdaschool.empoweredconversation.Utils.AuthUtils
import com.lambdaschool.empoweredconversation.service.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class UserRepo(application: Application) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getToken(username: String, password: String, tokenCallback: TokenCallback) {
        val ecApiService = RetrofitInstance.getService()
        val tokenSingle = ecApiService?.getToken(
            "password", username, password, AuthUtils.getBase64ApiCredentials()
        )
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<Token>() {
                override fun onSuccess(t: Token) {
                    tokenCallback.onTokenObtained(t)
                }

                override fun onError(e: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })

    }

    interface TokenCallback {
        fun onTokenObtained(token: Token)
    }
}