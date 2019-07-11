package com.lambdaschool.empoweredconversation.Utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

class AuthUtils {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun getBase64ApiCredentials(): String {
            val clientId = System.getenv("EC_CLIENT_ID")
            val clientSecret = System.getenv("EC_CLIENT_SECRET")
            val bytes = "$clientId:$clientSecret".toByteArray()
            return "Basic ${String(Base64.getEncoder().encode(bytes))}"
        }
    }
}