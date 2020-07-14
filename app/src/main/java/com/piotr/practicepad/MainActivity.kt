package com.piotr.practicepad

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.piotr.practicepad.databinding.MainActivityBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        if (savedInstanceState == null) {
            setNavigation()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setNavigation()
    }

    private fun setNavigation() {
        binding.bottomNavigation.setupWithNavController(
            navGraphIds = listOf(R.navigation.nav_exercise, R.navigation.nav_exercise_list),
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host,
            intent = intent
        )
    }
}

/*
* TODO
*  - keep state between tabs + animation
*  - should exerciseListViewModel have state? Probably true
*  - Create your own exercise set
*  - Test the app (list bugs - click being silent?)
*  - Testy! Unit + UI
*  - Test on other devices
*  - MVP?
*  - Further refactor? (pass callback to timers instead of events to fragment)
* */

/*DONE
*  - Detail exercise set view
*  - fragment_exercise viewmodel name -> model
*  - tempo change
*  - Design adjustment
*  - Create at least 6 valid Exercise Sets
*  - Add images

* */