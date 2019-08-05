package com.lambdaschool.empoweredconversation.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.lambdaschool.empoweredconversation.R
import com.lambdaschool.empoweredconversation.vm.LandingViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AppBarConfiguration.OnNavigateUpListener {
    private lateinit var landingViewModel: LandingViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        prefs = getSharedPreferences("AppIntro", Context.MODE_PRIVATE)

        if (!prefs.getBoolean("AppIntroViewed", false)) {
            startActivity(
                Intent(applicationContext, AppIntroActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            finish()
        }

        landingViewModel = ViewModelProviders.of(this).get(LandingViewModel::class.java)

        val navController = findNavController(R.id.nav_host_fragment)
        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        setupNavigation(navController)
        setupActionBar(navController, appBarConfiguration)
    }

    private fun setupActionBar(navController: NavController, appBarConfig: AppBarConfiguration) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    private fun setupNavigation(navController: NavController) {
        val sideNavView = findViewById<NavigationView>(R.id.nav_view)
        sideNavView?.setupWithNavController(navController)
        val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.landingFragment, R.id.conversationFragment, R.id.aboutFragment),
            drawerLayout
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val retValue = super.onCreateOptionsMenu(menu)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        if (navigationView == null) {
            menuInflater.inflate(R.menu.nav_menu, menu)
            return true
        }
        return retValue
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home ->
                drawer_layout.openDrawer(GravityCompat.START)
        }
        if (item != null)
            return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))

        return super.onOptionsItemSelected(item)
    }

}
