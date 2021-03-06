package com.piotr.practicepad.timers

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.piotr.practicepad.views.exercise.ExerciseEvent
import com.piotr.practicepad.views.exercise.PracticeState.State
import com.piotr.practicepad.views.exercise.PracticeState.State.*
import com.piotr.practicepad.ui.main.utils.Event

private const val ONE_SECOND = 1000L

class ExerciseSetTimer {
    val data: LiveData<Long> get() = mutableData
    private val mutableData: MutableLiveData<Long> = MutableLiveData<Long>()
    val event: LiveData<Event<ExerciseEvent>> get() = mutableEvent
    private val mutableEvent = MutableLiveData<Event<ExerciseEvent>>()
    private lateinit var timer: CountDownTimer

    fun setData(time: Long) {
        mutableData.value = time
        createNewTimer()
    }

    fun handleClick(state: State) {
        when (state) {
            ON, RESTART -> {
                createNewTimer()
                timer.start()
            }
            OFF -> timer.cancel()
        }
    }

    fun onPause() {
        timer.cancel()
    }

    private fun createNewTimer() {
        data.value?.let { time ->
            timer = object : CountDownTimer(
                time,
                ONE_SECOND
            ) {
                override fun onFinish() {
                    mutableEvent.value = Event(ExerciseEvent.SetEnded)
                    cancel()
                }

                override fun onTick(millisUntilFinished: Long) {
                    mutableData.value = millisUntilFinished
                }
            }
        }
    }
}
