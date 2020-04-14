package com.android.company.app.e_commerce.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.company.app.e_commerce.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{_ , navDistination : NavDestination , _ ->

            if(navDistination.id == R.id.categoryFragment || navDistination.id == R.id.ordersFragment ||
                navDistination.id == R.id.moreFragment || navDistination.id == R.id.cartFragment
            ){
                navView.visibility = android.view.View.VISIBLE
            }else navView.visibility = android.view.View.GONE

        }
    }
}
