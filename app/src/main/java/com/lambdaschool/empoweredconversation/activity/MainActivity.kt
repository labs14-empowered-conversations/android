package com.lambdaschool.empoweredconversation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.empoweredconversation.R
import com.lambdaschool.empoweredconversation.vm.LandingViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var landingViewModel: LandingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Home"

        landingViewModel = ViewModelProviders.of(this).get(LandingViewModel::class.java)
    }

}
