package com.lambdaschool.empoweredconversation

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.empoweredconversation.vm.LoginViewModel
import kotlinx.android.synthetic.main.activity_login_form.*

class LoginForm : AppCompatActivity() {
    private val TAG = "prefs"
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_form)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        button_login.setOnClickListener {
            button_register.text = "Register"
            edit_texts_linear_layout.visibility = View.VISIBLE
            edit_text_username.hint = "Enter Your username"
            edit_text_password.hint = "Enter Your password"
            if (edit_text_username.text.toString().isNotEmpty() &&
                edit_text_password.text.toString().isNotEmpty()) {
                loginViewModel.getToken(edit_text_username.text.toString(),
                    edit_text_password.text.toString()).observe(this, Observer {
                    if (it.access_token != null) {
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        intent.putExtra("token", it.access_token)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                })
            }
        }

        button_register.setOnClickListener {
            if (button_register.text == "Register"){
                edit_texts_linear_layout.visibility = View.VISIBLE
                button_register.text = "Create account"
                edit_text_username.hint = "Create a username"
                edit_text_password.hint = "Create a password"
            } else if (button_register.text == "Create account"){
                if (edit_text_username.text.toString().isNotEmpty() &&
                    edit_text_password.text.toString().isNotEmpty()) {
                    val result=
                        loginViewModel.createUser(User(edit_text_username.text.toString(), edit_text_password.text.toString()))
                    result.observe(this, Observer {
                        if (it){
                            Toast.makeText(applicationContext, "Account created!", Toast.LENGTH_LONG).show()
                            register_button.visibility = View.GONE
                            login_button.visibility = View.VISIBLE
                            edit_text_username.setText("")
                            edit_text_password.setText("")
                        } else {
                            Toast.makeText(applicationContext, "Account not created!", Toast.LENGTH_LONG).show()
                        }
                    })
                }
            }
        }
    }
}

