package com.lambdaschool.empoweredconversation.vm

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lambdaschool.empoweredconversation.Token
import com.lambdaschool.empoweredconversation.User
import com.lambdaschool.empoweredconversation.repository.UserRepo

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val userRepo = UserRepo()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getToken(username: String, password: String): LiveData<Token> {
        return userRepo.getToken(username, password)
    }

    fun createUser(user: User): LiveData<Boolean> {
        return userRepo.createUser(user)
    }

}