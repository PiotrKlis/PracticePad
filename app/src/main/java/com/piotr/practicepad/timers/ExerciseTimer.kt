package com.piotr.practicepad.timers

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import com.piotr.practicepad.exercise.ExerciseEvent
import com.piotr.practicepad.exercise.PracticeState
import com.piotr.practicepad.exercise.PracticeState.State.*
import com.piotr.practicepad.ui.main.utils.Event
import javax.inject.Inject

private const val ONE_SECOND = 1000L
private const val FIRST_ITEM = 0

class ExerciseTimer @Inject constructor(private val exerciseSetRepository: ExerciseSetRepository) {
    val data: LiveData<Long> get() = mutableData
    private val mutableData = MutableLiveData<Long>()
    val event: LiveData<Event<ExerciseEvent>> get() = mutableEvent
    private val mutableEvent = MutableLiveData<Event<ExerciseEvent>>()

    private lateinit var timer: CountDownTimer
    private var position = FIRST_ITEM

    //TODO: Check when timers init is invoked
    init {
        mutableData.value = exerciseSetRepository.getActiveSet().exerciseList[FIRST_ITEM].time
        createNewTimer(exerciseSetRepository.getActiveSet().exerciseList[FIRST_ITEM].time)
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

    fun startNextExercise(position: Int) {
        createNewTimer(exerciseSetRepository.getActiveSet().exerciseList[position].time)
        timer.start()
    }

    private fun createNewTimer(time: Long) {
        timer = object : CountDownTimer(
            time,
            ONE_SECOND
        ) {
            override fun onFinish() {
                if (exerciseSetRepository.getActiveSet().shouldStartNewTimer(position)) {
                    position += 1
                    mutableEvent.value = Event(ExerciseEvent.NextExercise(position))
                }
            }

            override fun onTick(millisUntilFinished: Long) {
                mutableData.value = millisUntilFinished
            }
        }
    }
}
