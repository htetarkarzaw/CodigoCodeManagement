package com.htetarkarzaw.codigocodemanagement.presentation

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.htetarkarzaw.codigocodemanagement.R
import com.htetarkarzaw.codigocodemanagement.databinding.ActivityMainBinding
import com.htetarkarzaw.codigocodemanagement.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(inflate = ActivityMainBinding::inflate) {

    private lateinit var navController: NavController // don't forget to initialize

    private val listener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_upcoming, R.id.navigation_popular -> shouldShowBottomNavigation(
                    true
                )
                else -> shouldShowBottomNavigation(false)
            }
        }

    override fun initUi() {
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_popular, R.id.navigation_upcoming
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        navView.setupWithNavController(navController)
    }

    override fun observe() {
    }

    private fun shouldShowBottomNavigation(flag: Boolean) {
        if (flag) {
            binding.navView.visibility = View.VISIBLE
        } else binding.navView.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        navController.removeOnDestinationChangedListener(listener)
        super.onPause()
    }


}