package com.piotr.practicepad.timers

import android.os.CountDownTimer
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import com.piotr.practicepad.exercise.ExerciseViewModel.Statetos
import com.piotr.practicepad.extensions.getOverallTime
import javax.inject.Inject

private const val ONE_SECOND = 1000L

class ExerciseSetTimerViewModel @Inject constructor(private val exerciseSetRepository: ExerciseSetRepository) :
    ViewModel() {
    val data = ObservableField(0L)

    //    private val mutableData = MutableLiveData<TestLong>()
    private var timeros: CountDownTimer? = null

    init {
        data.set(exerciseSetRepository.getActiveSet().exerciseList.getOverallTime())
    }

    fun handleClick(state: Statetos) {
        when (state) {
            Statetos.ON -> {
                timeros?.onFinish()
            }
            Statetos.OFF, Statetos.RESTART -> {
                initTimer(exerciseSetRepository.getActiveSet().exerciseList.getOverallTime())
            }
        }
    }

    private fun initTimer(time: Long) {
        timeros = object : CountDownTimer(time, ONE_SECOND) {
            override fun onFinish() {
                cancel()
            }

            override fun onTick(millisUntilFinished: Long) {
                data.set(millisUntilFinished)
            }
        }.start()
    }
}

data class TestLong(val time: Long = 0)