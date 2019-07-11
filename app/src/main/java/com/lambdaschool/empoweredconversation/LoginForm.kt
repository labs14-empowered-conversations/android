package com.lambdaschool.empoweredconversation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_form.*

class LoginForm : AppCompatActivity() {
    private val TAG = "prefs"
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_form)

        prefs = getSharedPreferences(TAG, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("username", "username")
        editor.putString("password", "password")
        editor.apply()

        button_login.setOnClickListener {
            if (edit_text_username.text.toString() == prefs.getString("username", null) &&
                edit_text_password.text.toString() == prefs.getString("password", null)) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}
