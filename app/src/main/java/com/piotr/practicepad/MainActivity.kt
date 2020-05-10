package com.piotr.practicepad

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.piotr.practicepad.databinding.MainActivityBinding
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        setNavigation()
    }

    private fun setNavigation() {
        supportFragmentManager.findFragmentById(R.id.nav_host)?.let { fragment ->
            val navigator = KeepStateNavigator(this, fragment.childFragmentManager, R.id.nav_host)
            val navController = findNavController(R.id.nav_host)
            navController.navigatorProvider.addNavigator(navigator)
            navController.setGraph(R.navigation.nav_graph)
            binding.bottomNavigation.setupWithNavController(navController)
        }
    }
}

/*
* TODO
*  - Add images
*  - Create at least 6 valid Exercise Sets
*  - Test the app (list bugs)
*  - Design adjustment
*  - Testy! Unit + UI
*  - Test on other devices
*  - MVP?
*  - Further refactor? (pass callback to timers instead of events to fragment)
* */