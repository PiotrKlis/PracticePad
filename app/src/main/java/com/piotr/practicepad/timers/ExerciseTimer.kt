package com.piotr.practicepad.timers

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.piotr.practicepad.views.exercise.ExerciseEvent
import com.piotr.practicepad.views.exercise.PracticeState
import com.piotr.practicepad.views.exercise.PracticeState.State.*
import com.piotr.practicepad.ui.main.utils.Event

private const val ONE_SECOND = 1000L
private const val FIRST_ITEM = 0

class ExerciseTimer {
    val data: LiveData<Long> get() = mutableData
    private val mutableData = MutableLiveData<Long>()
    val event: LiveData<Event<ExerciseEvent>> get() = mutableEvent
    private val mutableEvent = MutableLiveData<Event<ExerciseEvent>>()
    private lateinit var timer: CountDownTimer
    private var position = FIRST_ITEM

    fun setData(time: Long) {
        mutableData.value = time
        createNewTimer()
    }

    fun handleClick(state: PracticeState.State) {
        when (state) {
            ON, RESTART -> {
                position = FIRST_ITEM
                createNewTimer()
                timer.start()
            }
            OFF -> timer.cancel()
        }
    }

    fun onPause() {
        timer?.cancel()
    }

    fun startNextExercise(time: Long) {
        mutableData.value = time
        createNewTimer()
        timer.start()
    }

    private fun createNewTimer() {
        data.value?.let { time ->
            timer = object : CountDownTimer(
                time,
                ONE_SECOND
            ) {
                override fun onFinish() {
                    position += 1
                    mutableEvent.value = Event(ExerciseEvent.NextExercise(position))
                }

                override fun onTick(millisUntilFinished: Long) {
                    mutableData.value = millisUntilFinished
                }
            }
        }
    }
}
