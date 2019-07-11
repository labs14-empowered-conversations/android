package com.lambdaschool.empoweredconversation.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.lambdaschool.empoweredconversation.BuildConfig
import java.util.*

class AuthUtils {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun getBase64ApiCredentials(): String {
            val clientId = BuildConfig.clientId
            val clientSecret = BuildConfig.clientSecret
            val bytes = "$clientId:$clientSecret".toByteArray()
            return "Basic ${String(Base64.getEncoder().encode(bytes))}"
        }
    }
}