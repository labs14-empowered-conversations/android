package com.lambdaschool.empoweredconversation

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.empoweredconversation.utils.AuthUtils
import com.lambdaschool.empoweredconversation.vm.LoginViewModel
import kotlinx.android.synthetic.main.activity_login_form.*

class LoginForm : AppCompatActivity() {
    private val TAG = "prefs"
    private lateinit var loginViewModel: LoginViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_form)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        button_login.setOnClickListener {
            if (edit_text_username.text.toString().isNotEmpty() &&
                edit_text_password.text.toString().isNotEmpty()) {
                loginViewModel.getToken(edit_text_username.text.toString(),
                    edit_text_password.text.toString()).observe(this, Observer {
                    if (it.access_token != null) {
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                })
            }
        }
    }
}

