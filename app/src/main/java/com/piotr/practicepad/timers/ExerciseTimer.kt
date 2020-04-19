package com.piotr.practicepad.timers

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import com.piotr.practicepad.exercise.PracticeState
import com.piotr.practicepad.exercise.PracticeState.State.*
import javax.inject.Inject

private const val ONE_SECOND = 1000L
private const val FIRST_ITEM = 0

class ExerciseTimer @Inject constructor(private val exerciseSetRepository: ExerciseSetRepository) {
    val data: LiveData<Long> get() = mutableData
    private val mutableData: MutableLiveData<Long> = MutableLiveData<Long>()
    private lateinit var timer: CountDownTimer
    private var position = FIRST_ITEM

    init {
        mutableData.value = exerciseSetRepository.getActiveSet().exerciseList[position].time
        startNewTimer(exerciseSetRepository.getActiveSet().exerciseList[position].time)
    }

    fun handleClick(state: PracticeState.State) {
        when (state) {
            ON -> timer.start()
            OFF -> timer.cancel()
            RESTART -> timer.start()
        }
    }

    fun onPause() {
        timer.cancel()
    }

    private fun startNewTimer(time: Long) {
        timer = object : CountDownTimer(
            time,
            ONE_SECOND
        ) {
            override fun onFinish() {
                val activeSet = exerciseSetRepository.getActiveSet()
                cancel()
                if (activeSet.shouldStartNewTimer(position)) {
                    position += 1
                    startNewTimer(activeSet.exerciseList[position].time)
                    timer.start()
                }
            }

            override fun onTick(millisUntilFinished: Long) {
                mutableData.value = millisUntilFinished
            }
        }
    }
}
