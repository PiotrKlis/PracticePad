package com.piotr.practicepad

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.databinding.MainActivityBinding
import com.piotr.practicepad.extensions.setupWithNavController
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private var disposable: Disposable? = null

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        Log.d("XXX", "is first launch ${sharedPrefs.isFirstAppLaunch()}")
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
                Log.d("XXX", "inside subscribe")
                setNavigation()
                sharedPrefs.setFirstAppLaunch()
                Log.d("XXX", "initdb first launch ${sharedPrefs.isFirstAppLaunch()}")

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
*  - Metronome change on new exercise
*  - timer starts at 4:60 ?
*  - any change to current set (up/down/delete) to reflect in db
*  - Create your own exercise set
*  - Test the app (list bugs - click being silent?)
*  - Testy! Unit + UI
*  - Test on other devices
*  - MVP?
*  - Further refactor? (pass callback to timers instead of events to fragment)
*
*  MINORS
* - animation
* */

/*DONE
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