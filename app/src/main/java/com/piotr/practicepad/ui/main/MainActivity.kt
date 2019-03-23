package com.piotr.practicepad.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.data.ExerciseData
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navController = findNavController(this, R.id.nav_host)
        setupWithNavController(bottom_navigation, navController)
    }
}
