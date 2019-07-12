package com.lambdaschool.empoweredconversation.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lambdaschool.empoweredconversation.User
import com.lambdaschool.empoweredconversation.repository.UserRepo

class LandingViewModel(application: Application): AndroidViewModel(application) {
    private val userRepo = UserRepo()

    fun getAllUsers(token: String): LiveData<MutableList<User>> {
        return userRepo.getAllUsers(token)
    }
}