package com.piotr.practicepad

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.piotr.practicepad.databinding.MainActivityBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        setNavigation()
    }

    private fun setNavigation() {
        supportFragmentManager
            .findFragmentById(R.id.nav_host)
            ?.let { fragment ->
                val navController = findNavController(R.id.nav_host)
                navController.navigatorProvider.addNavigator(getNavigator(fragment))
                navController.setGraph(R.navigation.nav_graph)
                binding.bottomNavigation.setupWithNavController(navController)
            }
    }

    private fun getNavigator(fragment: Fragment): KeepStateNavigator {
        return KeepStateNavigator(
            this,
            fragment.childFragmentManager,
            R.id.nav_host
        )
    }
}

/*
* TODO
*  - Detail exercise set view
*  - fragment_exercise viewmodel name -> model
*  - should exerciseListViewModel have state? Probably true
*  - Create your own exercise set
*  - Test the app (list bugs - click being silent?)
*  - Testy! Unit + UI
*  - Test on other devices
*  - MVP?
*  - Further refactor? (pass callback to timers instead of events to fragment)
* */

/*DONE
*  - tempo change
*  - Design adjustment
*  - Create at least 6 valid Exercise Sets
*  - Add images

* */