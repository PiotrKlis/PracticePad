package com.piotr.practicepad.timers

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import com.piotr.practicepad.ui.main.utils.Event
import com.piotr.practicepad.views.exercise.Practice
import com.piotr.practicepad.views.exercise.Practice.State.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ONE_SECOND = 1000L

@OptIn(ExperimentalCoroutinesApi::class)
class ExerciseSetTimer @Inject constructor(private val practice: Practice) {
    val data get() = mutableData
    private val mutableData = MutableLiveData<Long>()
    val event get() = mutableEvent
    private val mutableEvent = MutableLiveData<Event<ExerciseSetTimerEvent>>()
    private lateinit var timer: CountDownTimer

    init {
//        GlobalScope.launch {
//            practice.state.collectLatest { state ->
//                when (state) {
//                    ON, RESTART -> {
//                        createTimer()
//                        timer.start()
//                    }
//                    OFF -> {
//                        timer.cancel()
//                    }
//                }
//            }
//        }
    }

    fun setData(time: Long) {
        mutableData.value = time
        GlobalScope.launch(Dispatchers.Main) {
            createTimer()
            practice.state.collectLatest { state ->
                when (state) {
                    ON, RESTART -> {
                        createTimer()
                        timer.start()
                    }
                    OFF -> {
                        timer.cancel()
                    }
                }
            }
        }
    }

    private fun createTimer() {
        data.value?.let { time ->
            timer = object : CountDownTimer(
                time,
                ONE_SECOND
            ) {
                override fun onFinish() {
                    mutableEvent.value = Event(ExerciseSetTimerEvent.ExerciseSetEnded)
                    cancel()
                }

                override fun onTick(millisUntilFinished: Long) {
                    mutableData.value = millisUntilFinished
                }
            }
        }
    }
}
