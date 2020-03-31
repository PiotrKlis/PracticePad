package com.piotr.practicepad.exercise

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import com.piotr.practicepad.extensions.getNextExerciseName
import com.piotr.practicepad.extensions.getOverallTime
import com.piotr.practicepad.metronome.Metronome
import com.piotr.practicepad.timers.ExerciseTimer
import com.piotr.practicepad.timers.ExerciseSetTimer
import javax.inject.Inject

private const val FIRST_ITEM = 0

class ExerciseViewModel @Inject constructor(
    private val metronome: Metronome,
    private val exerciseSetRepository: ExerciseSetRepository
) : ViewModel() {
    val state: LiveData<ExerciseState>
        get() = mutableExerciseState
    val isTimerOn = ObservableField(State.OFF)

    private val mutableExerciseState =
        MutableLiveData<ExerciseState>().apply { value = ExerciseState() }

    private lateinit var exerciseExerciseSetTimer: ExerciseSetTimer
    private lateinit var exerciseTimer: ExerciseTimer
    private var currentExerciseSetId: Int? = null

    enum class State {
        ON, OFF, RESTART
    }

    fun renderExerciseSet() {
        exerciseSetRepository.getActiveSet().let { activeExerciseSet ->
            if (currentExerciseSetId != activeExerciseSet.id) {
                isTimerOn.set(State.OFF)
                mutableExerciseState.value =
                    ExerciseState(
                        setName = activeExerciseSet.name,
                        exerciseImage = activeExerciseSet.exerciseList[FIRST_ITEM].image,
                        exerciseName = activeExerciseSet.exerciseList[FIRST_ITEM].name,
                        nextExerciseName = activeExerciseSet.exerciseList.getNextExerciseName(
                            FIRST_ITEM
                        ),
                        exercisesLeft = Pair(FIRST_ITEM, activeExerciseSet.exerciseList.size),
                        currentExerciseIndex = FIRST_ITEM,
                        exerciseList = activeExerciseSet.exerciseList,
                        tempo = activeExerciseSet.tempo
                    )
                exerciseTimer = ExerciseTimer(activeExerciseSet.exerciseList[FIRST_ITEM].time)
                exerciseExerciseSetTimer =
                    ExerciseSetTimer(activeExerciseSet.exerciseList.getOverallTime())
                currentExerciseSetId = activeExerciseSet.id
            }
        }
    }

    fun powerClick() {
        when (isTimerOn.get()) {
            State.ON -> {
                isTimerOn.set(State.OFF)
                exerciseExerciseSetTimer.onFinish()
                exerciseTimer.onFinish()
                metronome.cancel()
            }
            State.OFF -> {
                isTimerOn.set(State.ON)
                exerciseExerciseSetTimer.data.value?.let { exerciseExerciseSetTimer.onTick(it) }
                exerciseTimer.data.value?.let { exerciseTimer.onTick(it) }
                metronome.run()
            }
            State.RESTART -> {
                isTimerOn.set(State.ON)
                restart()
                exerciseExerciseSetTimer.data.value?.let { exerciseExerciseSetTimer.onTick(it) }
                exerciseTimer.data.value?.let { exerciseTimer.onTick(it) }
                metronome.run()
            }
        }
    }

    fun pauseTimers() {
        exerciseTimer.onFinish()
        exerciseExerciseSetTimer.onFinish()
    }

    private fun restart() {
        exerciseSetRepository.getActiveSet().let { activeExerciseSet ->
            mutableExerciseState.value =
                ExerciseState(
                    setName = activeExerciseSet.name,
                    exerciseImage = activeExerciseSet.exerciseList[FIRST_ITEM].image,
                    exerciseName = activeExerciseSet.exerciseList[FIRST_ITEM].name,
                    nextExerciseName = activeExerciseSet.exerciseList.getNextExerciseName(FIRST_ITEM),
                    exercisesLeft = Pair(FIRST_ITEM, activeExerciseSet.exerciseList.size),
                    currentExerciseIndex = FIRST_ITEM,
                    exerciseList = activeExerciseSet.exerciseList,
                    tempo = activeExerciseSet.tempo
                )
            exerciseTimer = ExerciseTimer(activeExerciseSet.exerciseList[FIRST_ITEM].time)
            exerciseExerciseSetTimer =
                ExerciseSetTimer(activeExerciseSet.exerciseList.getOverallTime())
        }
    }
}
