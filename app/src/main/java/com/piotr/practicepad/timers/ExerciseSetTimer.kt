package com.piotr.practicepad.timers

import android.os.CountDownTimer
import com.piotr.practicepad.ui.main.utils.Event
import com.piotr.practicepad.views.exercise.ExerciseEvent
import com.piotr.practicepad.views.exercise.Practice
import com.piotr.practicepad.views.exercise.Practice.State.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ONE_SECOND = 1000L

class ExerciseSetTimer @Inject constructor(private val practice: Practice) {
    val data get() = mutableData.asSharedFlow()
    private val mutableData = MutableSharedFlow<Long>()
    val event get() = mutableEvent.asSharedFlow()
    private val mutableEvent = MutableSharedFlow<Event<ExerciseEvent>>()
    private lateinit var timer: CountDownTimer

    init {
        GlobalScope.launch {
            practice.state.collectLatest { state ->
                when (state) {
                    ON, RESTART -> start()
                    OFF -> stop()
                }
            }
        }
    }

    suspend fun setData(time: Long) {
        mutableData.emit(time)
        start()
    }

    private suspend fun start() {
        GlobalScope.launch {
            val time = mutableData.first()
            timer = object : CountDownTimer(
                time,
                ONE_SECOND
            ) {
                override fun onFinish() {
                    GlobalScope.launch { mutableEvent.emit(Event(ExerciseEvent.SetEnded)) }
                    cancel()
                }

                override fun onTick(millisUntilFinished: Long) {
                    GlobalScope.launch { mutableData.emit(millisUntilFinished) }
                }
            }.start()
        }
    }

    private fun stop() {
        timer.cancel()
    }
}

