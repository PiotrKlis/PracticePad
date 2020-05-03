package com.piotr.practicepad.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import com.piotr.practicepad.exercise.PracticeState.State.*
import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.extensions.getNextExerciseName
import com.piotr.practicepad.extensions.getOverallTime
import com.piotr.practicepad.metronome.Metronome
import com.piotr.practicepad.timers.ExerciseSetTimer
import com.piotr.practicepad.timers.ExerciseTimer
import javax.inject.Inject

private const val FIRST_ITEM = 0

class ExerciseViewModel @Inject constructor(
    private val exerciseSetRepository: ExerciseSetRepository,
    private val metronome: Metronome,
    val exerciseTimer: ExerciseTimer,
    val exerciseSetTimer: ExerciseSetTimer,
    val practiceState: PracticeState
) : ViewModel() {
    val state: LiveData<ExerciseState> get() = mutableState
    private val mutableState = MutableLiveData<ExerciseState>().apply { value = ExerciseState() }
    private var activeSetId: Int? = null

    fun renderActiveExerciseSet() {
        exerciseSetRepository.getActiveSet().let { activeSet ->
            if (activeSetId != activeSet.id) {
                renderFirstItem(exerciseSetRepository.getActiveSet())
                activeSetId = activeSet.id
            }
        }
    }

    fun powerClick(state: PracticeState.State) {
        when (state) {
            ON -> pausePractice()
            OFF -> startPractice()
            RESTART -> {
                renderFirstItem(exerciseSetRepository.getActiveSet())
                startPractice()
            }
        }
    }

    fun onPause() {
        exerciseTimer.onPause()
        exerciseSetTimer.onPause()
        metronome.stop()
        practiceState.onPause()
    }

    fun renderNextExercise(position: Int) {
        val activeSet = exerciseSetRepository.getActiveSet()
        if (activeSet.shouldStartNextExercise(position)) {
            mutableState.value = getExercise(activeSet, position)
            exerciseTimer.startNextExercise(activeSet.exerciseList[position].time)
        } else {
            practiceState.setState(RESTART)
        }
    }

    fun setEnded() {
        metronome.stop()
        practiceState.setState(RESTART)
    }

    private fun startPractice() {
        exerciseTimer.handleClick(ON)
        exerciseSetTimer.handleClick(ON)
        metronome.handleClick(ON)
        practiceState.setState(ON)
    }

    private fun pausePractice() {
        exerciseTimer.handleClick(OFF)
        exerciseSetTimer.handleClick(OFF)
        metronome.handleClick(OFF)
        practiceState.setState(OFF)
    }

    private fun renderFirstItem(activeExerciseSet: ExerciseSet) {
        mutableState.value = getExercise(activeExerciseSet, FIRST_ITEM)
        exerciseTimer.setData(activeExerciseSet.exerciseList[FIRST_ITEM].time)
        exerciseSetTimer.setData(activeExerciseSet.exerciseList.getOverallTime())
        metronome.setData(activeExerciseSet.tempo)
    }

    private fun getExercise(exerciseSet: ExerciseSet, position: Int): ExerciseState {
        return ExerciseState(
            setName = exerciseSet.name,
            exerciseImage = exerciseSet.exerciseList[position].image,
            exerciseName = exerciseSet.exerciseList[position].name,
            nextExerciseName = exerciseSet.exerciseList.getNextExerciseName(position),
            exercisesLeft = Pair(position, exerciseSet.exerciseList.size),
            currentExerciseIndex = position,
            exerciseList = exerciseSet.exerciseList,
            tempo = exerciseSet.tempo.toLong()
        )
    }
}
