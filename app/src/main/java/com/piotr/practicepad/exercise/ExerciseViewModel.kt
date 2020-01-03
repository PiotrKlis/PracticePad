package com.piotr.practicepad.exercise

import android.os.CountDownTimer
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.data.repository.ExerciseDataRepository

private const val FIRST_ITEM = 0
private const val ONE_SECOND = 1000L

class ExerciseViewModel : ViewModel() {
    val state: LiveData<ExerciseState>
        get() = mutableExerciseState
    val isTimerOn = ObservableField(State.OFF)

    enum class State {
        ON, OFF, RESTART
    }

    private val mutableExerciseState =
        MutableLiveData<ExerciseState>().apply { value = ExerciseState() }

    private lateinit var setTimer: CountDownTimer
    private lateinit var exerciseTimer: CountDownTimer

    fun startNewExerciseSet() {
        ExerciseDataRepository().getActiveExerciseSet().let { activeExerciseSet ->
            mutableExerciseState.postValue(
                ExerciseState(
                    setName = activeExerciseSet.name,
                    setTimeLeft = getOverallTime(activeExerciseSet.exerciseList),
                    exerciseImage = activeExerciseSet.exerciseList[FIRST_ITEM].image,
                    exerciseName = activeExerciseSet.exerciseList[FIRST_ITEM].name,
                    nextExerciseName = getNextExerciseName(
                        activeExerciseSet.exerciseList,
                        FIRST_ITEM
                    ),
                    exercisesLeft = Pair(FIRST_ITEM, activeExerciseSet.exerciseList.size),
                    currentExerciseIndex = FIRST_ITEM,
                    exerciseList = activeExerciseSet.exerciseList,
                    exerciseTimeLeft = activeExerciseSet.exerciseList[FIRST_ITEM].time
                )
            )
        }
    }

    fun powerClick() {
        when (isTimerOn.get()) {
            State.ON -> {
                isTimerOn.set(State.OFF)
                stopSetTimer()
                stopExerciseTimer()
            }
            State.OFF -> {
                isTimerOn.set(State.ON)
                startSetTimer()
                startExerciseTimer()
            }
            State.RESTART -> {
                isTimerOn.set(State.ON)
                startNewExerciseSet()
                startSetTimer()
                startExerciseTimer()
            }
        }
    }

    private fun getOverallTime(exerciseList: ArrayList<Exercise>): Long {
        return exerciseList
            .map { it.time }
            .sum()
    }

    private fun getNextExerciseName(exerciseList: ArrayList<Exercise>, index: Int): String {
        var result = "Last one"
        if (index + 1 < exerciseList.size) {
            result = exerciseList[index + 1].name
        }
        return result
    }

    private fun startSetTimer() {
        setTimer = object :
            CountDownTimer(mutableExerciseState.value?.setTimeLeft ?: ONE_SECOND, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                mutableExerciseState.value?.let { state ->
                    mutableExerciseState.value = state.copy(setTimeLeft = millisUntilFinished)
                }
            }

            override fun onFinish() {
                setTimer.cancel()
                isTimerOn.set(State.RESTART)
            }
        }.start()
    }

    private fun stopSetTimer() {
        setTimer.cancel()
    }

    private fun startExerciseTimer() {
        exerciseTimer = object :
            CountDownTimer(mutableExerciseState.value?.exerciseTimeLeft ?: ONE_SECOND, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                mutableExerciseState.value?.let { state ->
                    mutableExerciseState.value = state.copy(exerciseTimeLeft = millisUntilFinished)
                }
            }

            override fun onFinish() {
                exerciseTimer.cancel()
                mutableExerciseState.value?.let { state ->
                    if (state.currentExerciseIndex + 1 < state.exerciseList.size) {
                        mutableExerciseState.value =
                            state.copy(
                                currentExerciseIndex = state.currentExerciseIndex + 1,
                                nextExerciseName = getNextExerciseName(
                                    state.exerciseList,
                                    state.currentExerciseIndex + 1
                                ),
                                exercisesLeft = Pair(
                                    state.currentExerciseIndex + 1,
                                    state.exerciseList.size
                                ),
                                exerciseName = state.exerciseList[state.currentExerciseIndex + 1].name,
                                exerciseImage = state.exerciseList[state.currentExerciseIndex + 1].image,
                                exerciseTimeLeft = state.exerciseList[state.currentExerciseIndex + 1].time
                            )
                        startExerciseTimer()
                    }
                }
            }
        }.start()
    }

    private fun stopExerciseTimer() {
        exerciseTimer.cancel()
    }
}
