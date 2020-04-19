package com.piotr.practicepad.timers

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.piotr.practicepad.exercise.PracticeState
import com.piotr.practicepad.exercise.PracticeState.State.*
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import javax.inject.Inject

private const val ONE_SECOND = 1000L
private const val FIRST_ITEM = 0

class ExerciseTimer @Inject constructor(private val exerciseSetRepository: ExerciseSetRepository) {
    val data: LiveData<Long> get() = mutableData
    private val mutableData: MutableLiveData<Long> = MutableLiveData<Long>()

    init {
        mutableData.value = exerciseSetRepository.getActiveSet().exerciseList[FIRST_ITEM].time
    }

    fun handleClick(state: PracticeState.State) {
        when (state) {
            ON -> timer.start()
            OFF -> timer.cancel()
            RESTART -> timer.start()
        }
    }

    fun onPause() {
        timer.onFinish()
    }

    private val timer = object : CountDownTimer(
        exerciseSetRepository.getActiveSet().exerciseList[FIRST_ITEM].time,
        ONE_SECOND
    ) {
        override fun onFinish() {
            cancel()
        }

        override fun onTick(millisUntilFinished: Long) {
            mutableData.value = millisUntilFinished
        }
    }
}
