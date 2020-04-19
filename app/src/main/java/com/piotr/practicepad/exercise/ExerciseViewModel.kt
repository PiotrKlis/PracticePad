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
    val event: LiveData<Event<ExerciseEvent>>
        get() = mutableEvent
    val state: LiveData<ExerciseState>
        get() = mutableState

    private val mutableState = MutableLiveData<ExerciseState>().apply { value = ExerciseState() }
    private val mutableEvent = MutableLiveData<Event<ExerciseEvent>>()
    private var currentExerciseSetId: Int? = null

    fun renderExerciseSet() {
        exerciseSetRepository.getActiveSet().let { activeExerciseSet ->
            if (currentExerciseSetId != activeExerciseSet.id) {
                mutableState.value = getFirstExercise(activeExerciseSet)
                currentExerciseSetId = activeExerciseSet.id
            }
        }
    }

    fun powerClick() {
        when (PracticeState().state.value) {
            ON -> mutableEvent.value = Event(ExerciseEvent.PowerClick(ON))
            OFF -> mutableEvent.value = Event(ExerciseEvent.PowerClick(OFF))
            RESTART -> {
                mutableEvent.value = Event(ExerciseEvent.PowerClick(RESTART))
                restart()
            }
        }
    }

    fun onPause() {
        mutableEvent.value = Event(ExerciseEvent.OnPause)
    }

    private fun restart() {
        exerciseSetRepository.getActiveSet().let { activeExerciseSet ->
            mutableState.value = getFirstExercise(activeExerciseSet)
        }
    }

    private fun getFirstExercise(exerciseSet: ExerciseSet): ExerciseState {
        return ExerciseState(
            setName = exerciseSet.name,
            exerciseImage = exerciseSet.exerciseList[FIRST_ITEM].image,
            exerciseName = exerciseSet.exerciseList[FIRST_ITEM].name,
            nextExerciseName = exerciseSet.exerciseList.getNextExerciseName(FIRST_ITEM),
            exercisesLeft = Pair(FIRST_ITEM, exerciseSet.exerciseList.size),
            currentExerciseIndex = FIRST_ITEM,
            exerciseList = exerciseSet.exerciseList,
            tempo = exerciseSet.tempo.toLong()
        )
    }
}
