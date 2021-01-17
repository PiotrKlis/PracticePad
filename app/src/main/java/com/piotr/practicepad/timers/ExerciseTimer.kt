package com.piotr.practicepad.timers

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.piotr.practicepad.views.exercise.ExerciseTimerEvent
import com.piotr.practicepad.views.exercise.Practice
import com.piotr.practicepad.views.exercise.Practice.State.*
import com.piotr.practicepad.ui.main.utils.Event
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ONE_SECOND = 1000L

class ExerciseTimer @Inject constructor(private val practice: Practice) {
    val data: LiveData<Long> get() = mutableData
    private val mutableData = MutableLiveData<Long>()
    val timerEvent: LiveData<Event<ExerciseTimerEvent>> get() = mutableEvent
    private val mutableEvent = MutableLiveData<Event<ExerciseTimerEvent>>()
    private lateinit var timer: CountDownTimer

    init {
        GlobalScope.launch {
            practice.state.collectLatest { state ->
                when (state) {
                    ON -> {
                        createTimer()
                        timer.start()
                    }
                    OFF, RESTART -> timer.cancel()
                }
            }
        }
    }

    fun setData(time: Long) {
        mutableData.value = time
        createTimer()
    }

    fun startNextExercise(time: Long) {
        setData(time)
        timer.start()
    }

    private fun createTimer() {
        data.value?.let { time ->
            timer = object : CountDownTimer(
                time,
                ONE_SECOND
            ) {
                override fun onFinish() {
                    mutableEvent.value = Event(ExerciseTimerEvent.ExerciseEnded)
                    cancel()
                }

                override fun onTick(millisUntilFinished: Long) {
                    mutableData.value = millisUntilFinished
                }
            }
        }
    }
}
