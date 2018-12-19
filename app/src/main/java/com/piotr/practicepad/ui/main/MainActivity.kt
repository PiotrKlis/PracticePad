package com.piotr.practicepad.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.Exercise.ExerciseFragment
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseListFragment
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ExerciseFragment.newInstance())
                    .commitNow()
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ExerciseListFragment.newInstance())
                    .commitNow()
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        bottom_navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navigateToInitialScreen()
    }

    private fun navigateToInitialScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ExerciseFragment.newInstance())
            .commitNow()
    }


}
