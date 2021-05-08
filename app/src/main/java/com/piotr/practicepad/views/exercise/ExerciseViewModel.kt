package com.piotr.practicepad.views.exercise

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import com.piotr.practicepad.extensions.getNextExerciseName
import com.piotr.practicepad.extensions.getOverallTime
import com.piotr.practicepad.metronome.Metronome
import com.piotr.practicepad.timers.ExerciseSetTimer
import com.piotr.practicepad.timers.ExerciseTimer
import com.piotr.practicepad.views.exercise.PracticeState.State.*
import com.piotr.practicepad.views.exerciseSetList.ExerciseSet
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val FIRST_ITEM = 0

sealed class MetronomeEvent {
    object Start : MetronomeEvent()
    object Stop : MetronomeEvent()
    data class TempoChange(val tempo: Int) : MetronomeEvent()
}

class ExerciseViewModel @Inject constructor(
    private val exerciseSetRepository: ExerciseSetRepository,
    private val metronome: Metronome,
    val exerciseTimer: ExerciseTimer,
    val exerciseSetTimer: ExerciseSetTimer,
    val practiceState: PracticeState
) : ViewModel() {
    val metronomeEvent: SharedFlow<MetronomeEvent> get() = mutableMetronomeEvent
    private val mutableMetronomeEvent: MutableSharedFlow<MetronomeEvent> = MutableSharedFlow()

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
            OFF -> startPractice(ON)
            RESTART -> {
                viewModelScope.launch {
                    renderFirstItem(exerciseSetRepository.getActiveSet())
                    startPractice(ON)
                }
            }
        }
    }

    fun subtractTempoClick(tempo: Long) {
        viewModelScope.launch {
            val newTempo = tempo - 1
            mutableMetronomeEvent.emit(MetronomeEvent.TempoChange(newTempo.toInt()))
            mutableState.value = mutableState.value?.copy(tempo = newTempo)
        }
    }

    fun addTempoClick(tempo: Long) {
        viewModelScope.launch {
            val newTempo = tempo + 1
            mutableMetronomeEvent.emit(MetronomeEvent.TempoChange(newTempo.toInt()))
            mutableState.value = mutableState.value?.copy(tempo = newTempo)
        }
    }

    fun onPause() {
        exerciseTimer.onPause()
        exerciseSetTimer.onPause()
        practiceState.onPause()
        viewModelScope.launch {
            mutableMetronomeEvent.emit(MetronomeEvent.Stop)
        }
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
                Log.d("PKPK", "my position is $position")
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
        viewModelScope.launch {
            mutableMetronomeEvent.emit(MetronomeEvent.Stop)
        }
        practiceState.setState(RESTART)
        exerciseTimer.setEnded()
    }

    private fun startPractice(state: PracticeState.State) {
        exerciseTimer.handleClick(state)
        exerciseSetTimer.handleClick(state)
//        metronome.handleClick(ON, state.value?.tempo)
        viewModelScope.launch {
            mutableMetronomeEvent.emit(MetronomeEvent.Start)
        }
        practiceState.setState(state)
    }

    private fun pausePractice() {
        exerciseTimer.handleClick(OFF)
        exerciseSetTimer.handleClick(OFF)
//        metronome.handleClick(OFF, state.value?.tempo)
        viewModelScope.launch {
            mutableMetronomeEvent.emit(MetronomeEvent.Stop)
        }
        practiceState.setState(OFF)
    }
}
