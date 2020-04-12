package com.piotr.practicepad.exercise

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.extensions.getNextExerciseName
import com.piotr.practicepad.extensions.getOverallTime
import com.piotr.practicepad.metronome.Metronome
import com.piotr.practicepad.timers.ExerciseTimer
import com.piotr.practicepad.ui.main.utils.Event
import javax.inject.Inject

private const val FIRST_ITEM = 0

class ExerciseViewModel @Inject constructor(
    private val metronome: Metronome,
    private val exerciseSetRepository: ExerciseSetRepository
) : ViewModel() {
    val isTimerOn = ObservableField(Statetos.OFF)
    val event: LiveData<Event<ExerciseEvent>>
        get() = mutableEvent
    val state: LiveData<ExerciseState>
        get() = mutableState

    private val mutableState = MutableLiveData<ExerciseState>().apply { value = ExerciseState() }
    private val mutableEvent = MutableLiveData<Event<ExerciseEvent>>()
    private lateinit var exerciseTimer: ExerciseTimer
    private var currentExerciseSetId: Int? = null

    enum class Statetos {
        ON, OFF, RESTART
    }

    fun renderExerciseSet() {
        exerciseSetRepository.getActiveSet().let { activeExerciseSet ->
            if (currentExerciseSetId != activeExerciseSet.id) {
                isTimerOn.set(Statetos.OFF)
                mutableState.value = getExercise(activeExerciseSet)
                exerciseTimer = ExerciseTimer(activeExerciseSet.exerciseList[FIRST_ITEM].time)
                currentExerciseSetId = activeExerciseSet.id
            }
        }
    }

    fun powerClick() {
        when (isTimerOn.get()) {
            Statetos.ON -> {
                isTimerOn.set(Statetos.OFF)
                mutableEvent.value = Event(ExerciseEvent.PowerClick(Statetos.ON))
//                timerViewModel.timer.onFinish()
//                exerciseTimer.onFinish()
                metronome.cancel()
            }
            Statetos.OFF -> {
                isTimerOn.set(Statetos.ON)
                mutableEvent.value = Event(ExerciseEvent.PowerClick(Statetos.OFF))

//                timerViewModel.data.value?.let { timerViewModel.timer.onTick(it) }
//                exerciseTimer.data.value?.let { exerciseTimer.onTick(it) }
                metronome.run()
            }
            Statetos.RESTART -> {
                exerciseSetRepository.getActiveSet().let { activeExerciseSet ->
                    mutableEvent.value = Event(ExerciseEvent.PowerClick(Statetos.RESTART, activeExerciseSet.exerciseList.getOverallTime()))
                }
                isTimerOn.set(Statetos.ON)
                restart()
//                timerViewModel.data.value?.let { timerViewModel.timer.onTick(it) }
//                exerciseTimer.data.value?.let { exerciseTimer.onTick(it) }
                metronome.run()
            }
        }
    }

    private fun restart() {
        exerciseSetRepository.getActiveSet().let { activeExerciseSet ->
            mutableState.value = getExercise(activeExerciseSet)
//            exerciseTimer = ExerciseTimer(activeExerciseSet.exerciseList[FIRST_ITEM].time)
//            timerViewModel = ExerciseSetTimerViewModel(activeExerciseSet.exerciseList.getOverallTime())
        }
    }

    private fun getExercise(exerciseSet: ExerciseSet): ExerciseState {
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
