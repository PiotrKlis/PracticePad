package com.piotr.practicepad.views.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import com.piotr.practicepad.views.exercise.PracticeState.State.*
import com.piotr.practicepad.views.exerciseSetList.ExerciseSet
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
    private val metronomeOperationRange = 40 until 221
    private var previousSet: ExerciseSet? = null

    fun renderActiveExerciseSet() {
        viewModelScope.launch {
            exerciseSetRepository.getActiveSet().let { activeSet ->
                if (previousSet != activeSet) {
                    renderFirstItem(activeSet)
                    previousSet = activeSet
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

    private fun renderFirstItem(activeExerciseSet: ExerciseSet) {
        mutableState.value = createState(activeExerciseSet)
        exerciseTimer.setData(activeExerciseSet.exercises[FIRST_ITEM].time)
        exerciseSetTimer.setData(activeExerciseSet.exercises.getOverallTime())
        metronome.tempo = mutableState.value?.tempo
    }

    fun renderNextExercise(position: Int) {
        viewModelScope.launch {
            val activeSet = exerciseSetRepository.getActiveSet()
            if (activeSet.shouldStartNextExercise(position)) {
                mutableState.value = updateState(activeSet, position)
                exerciseTimer.startNextExercise(activeSet.exercises[position].time)
                metronome.tempo = mutableState.value?.tempo
            } else {
                practiceState.setState(RESTART)
            }
        }
    }

    private fun createState(exerciseSet: ExerciseSet): ExerciseState {
        return ExerciseState(
            setName = exerciseSet.title,
            exerciseImage = exerciseSet.exercises[FIRST_ITEM].image,
            exerciseName = exerciseSet.exercises[FIRST_ITEM].title,
            nextExerciseName = exerciseSet.exercises.getNextExerciseName(FIRST_ITEM),
            exercisesLeft = Pair(FIRST_ITEM, exerciseSet.exercises.size),
            currentExerciseIndex = FIRST_ITEM,
            exerciseList = exerciseSet.exercises,
            tempo = exerciseSet.tempo
        )
    }

    private fun updateState(
        activeSet: ExerciseSet,
        position: Int
    ) = mutableState.value?.copy(
        setName = activeSet.title,
        exerciseImage = activeSet.exercises[position].image,
        exerciseName = activeSet.exercises[position].title,
        nextExerciseName = activeSet.exercises.getNextExerciseName(position),
        exercisesLeft = Pair(position, activeSet.exercises.size),
        currentExerciseIndex = position,
        exerciseList = activeSet.exercises
    )

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

    private fun updateTempo(newTempo: Long) {
        if (newTempo in metronomeOperationRange) {
            mutableState.value = mutableState.value?.copy(tempo = newTempo)
            metronome.tempo = newTempo
            metronome.start()
        }
    }
}
