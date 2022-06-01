package com.example.paradox

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.paradox.controller.activities.MainActivity
import com.example.paradox.controller.activities.SharedPreferences
import com.example.paradox.databinding.ActivityNavigationEmployeerBinding

class NavigationEmployeerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationEmployeerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationEmployeerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarNavigationEmployeer.toolbar)

        binding.appBarNavigationEmployeer.toolbar.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_employeer)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_gallery, R.id.nav_slideshow, R.id.publishedWorksFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_salir, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sharedPreferences = SharedPreferences(this@NavigationEmployeerActivity)
        sharedPreferences.clearSharedPreferences()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_employeer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}