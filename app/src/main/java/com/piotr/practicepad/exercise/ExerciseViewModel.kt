package com.piotr.practicepad.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import com.piotr.practicepad.exercise.PracticeState.State.*
import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.extensions.getNextExerciseName
import com.piotr.practicepad.extensions.getOverallTime
import com.piotr.practicepad.metronome.Metronome
import com.piotr.practicepad.timers.ExerciseSetTimer
import com.piotr.practicepad.timers.ExerciseTimer
import kotlinx.coroutines.launch
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
    private val mutableState = MutableLiveData(ExerciseState())
    private val operationRange = 40 until 221
    private var activeSetId: Int? = null

    fun renderActiveExerciseSet() {
        viewModelScope.launch {
            exerciseSetRepository.getActiveSet().let { activeSet ->
                if (activeSetId != activeSet.id) {
                    viewModelScope.launch {
                        renderFirstItem(activeSet)
                        activeSetId = activeSet.id
                    }
                }
            }
        }
    }

    fun powerClick(state: PracticeState.State) {
        when (state) {
            ON -> pausePractice()
            OFF -> startPractice()
            RESTART -> {
                viewModelScope.launch {
                    renderFirstItem(exerciseSetRepository.getActiveSet())
                    startPractice()
                }
            }
        }
    }

    fun subtractTempoClick(tempo: Long) {
        updateTempo(tempo - 1)
    }

    fun addTempoClick(tempo: Long) {
        updateTempo(tempo + 1)
    }

    fun onPause() {
        exerciseTimer.onPause()
        exerciseSetTimer.onPause()
        metronome.stop()
        practiceState.onPause()
    }

    fun renderNextExercise(position: Int) {
        viewModelScope.launch {
            val activeSet = exerciseSetRepository.getActiveSet()
            if (activeSet.shouldStartNextExercise(position)) {
                mutableState.value = getExercise(activeSet, position)
                exerciseTimer.startNextExercise(activeSet.exercises[position].time)
            } else {
                practiceState.setState(RESTART)
            }
        }
    }

    fun setEnded() {
        metronome.stop()
        practiceState.setState(RESTART)
    }

    private fun startPractice() {
        exerciseTimer.handleClick(ON)
        exerciseSetTimer.handleClick(ON)
        metronome.handleClick(ON, state.value?.tempo)
        practiceState.setState(ON)
    }

    private fun pausePractice() {
        exerciseTimer.handleClick(OFF)
        exerciseSetTimer.handleClick(OFF)
        metronome.handleClick(OFF, state.value?.tempo)
        practiceState.setState(OFF)
    }

    private fun renderFirstItem(activeExerciseSet: ExerciseSet) {
        mutableState.value = getExercise(activeExerciseSet, FIRST_ITEM)
        exerciseTimer.setData(activeExerciseSet.exercises[FIRST_ITEM].time)
        exerciseSetTimer.setData(activeExerciseSet.exercises.getOverallTime())
    }

    private fun getExercise(exerciseSet: ExerciseSet, position: Int): ExerciseState {
        return ExerciseState(
            setName = exerciseSet.title,
            exerciseImage =  exerciseSet.exercises[position].image,
            exerciseName = exerciseSet.exercises[position].title,
            nextExerciseName = exerciseSet.exercises.getNextExerciseName(position),
            exercisesLeft = Pair(position, exerciseSet.exercises.size),
            currentExerciseIndex = position,
            exerciseList = exerciseSet.exercises,
            tempo = exerciseSet.tempo.toLong()
        )
    }

    private fun updateTempo(newTempo: Long) {
        if (newTempo in operationRange) {
            mutableState.value = state.value?.copy(tempo = newTempo)
            changeMetronomeTempo(newTempo)
        }
    }

    private fun changeMetronomeTempo(newTempo: Long) {
        if (practiceState.state.value == ON) {
            metronome.changeTempo(newTempo)
        }
    }
}
