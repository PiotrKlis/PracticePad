package com.piotr.practicepad

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.databinding.MainActivityBinding
import com.piotr.practicepad.extensions.setupWithNavController
import com.piotr.practicepad.metronome.MetronomeService
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        handleFirstAppLaunch()
        val intent = Intent(this, MetronomeService::class.java)
        startService(intent)
    }

    private fun handleFirstAppLaunch() {
        if (sharedPrefs.isFirstAppLaunch()) {
            initDb()
        } else {
            setNavigation()
        }
    }

    private fun initDb() {
        PracticePadRoomDatabase.initDb(applicationContext)
        GlobalScope.launch(Dispatchers.Main) {
            PracticePadRoomDatabase.subject.asFlow().collect {
                setNavigation()
                sharedPrefs.setFirstAppLaunch()
            }
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
*  - Time in cards
*  - Overall time after increase in card
*  - exercise running after onPause/onResume
*  - Create your own exercise set
*  - Test the app (list bugs - click being silent?)
*  - Testy! Unit + UI
*  - Test on other devices
*  - MVP?
*  - Further refactor? (pass callback to timers instead of events to fragment)
*
*  MINORS
* - animation
* *  - timer starts at 4:60
* */

/*DONE
*  - any change to current set (up/down/delete) to reflect in db
*  - Metronome change on new exercise
*  - enum to file
*  - should exerciseListViewModel have state? Probably true
*  - keep state between tabs + animation
*  - Detail exercise set view
*  - fragment_exercise viewmodel name -> model
*  - tempo change
*  - Design adjustment
*  - Create at least 6 valid Exercise Sets
*  - Add images

* */