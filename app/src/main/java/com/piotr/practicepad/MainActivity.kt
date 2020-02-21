package com.piotr.practicepad

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.piotr.practicepad.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        supportFragmentManager.findFragmentById(R.id.nav_host)?.let {fragment ->
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
*  - Create at least 6 valid Exercise Sets
*  - Test the app
*  - Design adjustment
*  - Test on other devices
*  - Refactor (Dagger)
*  - MVP?
*  - Figure out from where get good quality Exercise pictures
*  - Create your own exercise set
*  - Firebase integration (auto-new-sets-upload)
* */