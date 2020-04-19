package com.piotr.practicepad.timers

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import com.piotr.practicepad.PracticeState
import com.piotr.practicepad.PracticeState.State.*
import com.piotr.practicepad.extensions.getOverallTime
import javax.inject.Inject

private const val ONE_SECOND = 1000L
private const val FIRST_ITEM = 0

class ExerciseTimer @Inject constructor(private val exerciseSetRepository: ExerciseSetRepository) {
    val data: LiveData<Long> get() = mutableData
    private val mutableData: MutableLiveData<Long> =
        MutableLiveData<Long>().apply { exerciseSetRepository.getActiveSet().exerciseList[FIRST_ITEM].time }

    fun handleClick(state: PracticeState.State) {
        when (state) {
            ON -> timer.onFinish()
            OFF -> {
                data.value?.let {
                    timer.onTick(it)
                }
            }
            RESTART -> ExerciseSetTimer(exerciseSetRepository)
        }
    }

    fun onPause() {
        timer.onFinish()
    }

    private val timer = object : CountDownTimer(
        exerciseSetRepository.getActiveSet().exerciseList.getOverallTime(),
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
