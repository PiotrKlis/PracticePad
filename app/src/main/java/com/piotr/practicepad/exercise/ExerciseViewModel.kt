package com.piotr.practicepad.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import com.piotr.practicepad.exercise.PracticeState.State.*
import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.extensions.getNextExerciseName
import com.piotr.practicepad.ui.main.utils.Event
import javax.inject.Inject

private const val FIRST_ITEM = 0

class ExerciseViewModel @Inject constructor(private val exerciseSetRepository: ExerciseSetRepository) :
    ViewModel() {
    val event: LiveData<Event<ExerciseEvent>> get() = mutableEvent
    val state: LiveData<ExerciseState> get() = mutableState

    private val mutableState = MutableLiveData<ExerciseState>().apply { value = ExerciseState() }
    private val mutableEvent = MutableLiveData<Event<ExerciseEvent>>()
    private var currentExerciseSetId: Int? = null

    fun renderExerciseSet() {
        exerciseSetRepository.getActiveSet().let { activeExerciseSet ->
            if (currentExerciseSetId != activeExerciseSet.id) {
                mutableState.value = getExercise(activeExerciseSet, FIRST_ITEM)
                currentExerciseSetId = activeExerciseSet.id
            }
        }
    }

    fun powerClick(state: PracticeState.State) {
        when (state) {
            ON -> mutableEvent.value = Event(ExerciseEvent.PowerClick(OFF))
            OFF -> mutableEvent.value = Event(ExerciseEvent.PowerClick(ON))
            RESTART -> {
                mutableEvent.value = Event(ExerciseEvent.PowerClick(ON))
                restart()
            }
        }
    }

    fun onPause() {
        mutableEvent.value = Event(ExerciseEvent.OnPause)
    }

    fun renderNextExercise(position: Int) {
        exerciseSetRepository.getActiveSet().let { exerciseSet ->
            mutableState.value = getExercise(exerciseSet, position)
        }
    }

    private fun restart() {
        exerciseSetRepository.getActiveSet().let { activeExerciseSet ->
            mutableState.value = getExercise(activeExerciseSet, FIRST_ITEM)
        }
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
