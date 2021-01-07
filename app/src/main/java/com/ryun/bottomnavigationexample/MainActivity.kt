package com.ryun.bottomnavigationexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment,R.id.searchFragment,R.id.menuFragment)
        )

        navController = nav_host_fragment.findNavController()

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id){
                R.id.homeFragment, R.id.searchFragment, R.id.menuFragment -> setBottomNavVisibility(true)
                else -> setBottomNavVisibility(false)
            }
        }
        bottom_nav_bar.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_container).navigateUp(appBarConfiguration)
    }

    private fun setBottomNavVisibility(isVisible: Boolean){
        bottom_nav_bar.isVisible = isVisible
    }
}