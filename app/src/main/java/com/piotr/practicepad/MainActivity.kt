package com.piotr.practicepad

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.piotr.practicepad.KeepStateNavigator
import com.piotr.practicepad.R
import com.piotr.practicepad.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host)!!
//        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.exerciseSetFragment)
//        val navController = findNavController(this, R.id.nav_host)
//
//        navController.navigatorProvider.addNavigator(navigator)
//        setupWithNavController(bottom_navigation, navController)

        //

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host)!!
        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.nav_host)
        val navController = findNavController(R.id.nav_host)
        navController.navigatorProvider.addNavigator(navigator)
        navController.setGraph(R.navigation.nav_graph)
        binding.bottomNavigation.setupWithNavController(navController)
    }

    /*
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)

        // get fragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!

        // setup custom navigator
        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.nav_host_fragment)
        navController.navigatorProvider += navigator

        // set navigation graph
        navController.setGraph(R.navigation.nav_graph)

        binding.bottomNav.setupWithNavController(navController)*/
}
