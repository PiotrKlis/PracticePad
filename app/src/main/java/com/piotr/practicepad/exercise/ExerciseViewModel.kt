package com.piotr.practicepad.exercise

import android.os.CountDownTimer
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.data.repository.ExerciseDataRepository
import com.piotr.practicepad.utils.Event

private const val FIRST_ITEM = 0

class ExerciseViewModel : ViewModel() {
    private lateinit var setTimer: CountDownTimer
    private lateinit var exerciseTimer: CountDownTimer

    private val mutableExerciseState =
        MutableLiveData<ExerciseState>().apply { value = ExerciseState() }
    private val mutableExerciseEvent = MutableLiveData<Event<ExerciseState>>()

    private val mutableExerciseSetState =
        MutableLiveData<ExerciseSetState>().apply { value = ExerciseSetState() }
    private val mutableExerciseSetEvent = MutableLiveData<Event<ExerciseSetState>>()

    val exerciseState: LiveData<ExerciseState>
        get() = mutableExerciseState
    val exerciseEvent: LiveData<Event<ExerciseState>>
        get() = mutableExerciseEvent

    val exerciseSetState: LiveData<ExerciseSetState>
        get() = mutableExerciseSetState
    val exerciseSetEvent: LiveData<Event<ExerciseSetState>>
        get() = mutableExerciseSetEvent

    val isTimerOn = ObservableField(State.OFF)

    enum class State {
        ON, OFF, RESTART
    }

    fun startNewExerciseSet() {
        ExerciseDataRepository().getActiveExerciseSet().let { activeExerciseSet ->
            Log.d("AAA ACTIVE", activeExerciseSet.toString())
            mutableExerciseSetState.postValue(
                ExerciseSetState(
                    name = activeExerciseSet.title,
                    timeLeft = getOverallTime(activeExerciseSet.exerciseList),
                    nextName = getNextExerciseName(activeExerciseSet.exerciseList, FIRST_ITEM),
                    currentExerciseIndex = FIRST_ITEM,
                    exerciseList = activeExerciseSet.exerciseList
                )
            )

            mutableExerciseState.postValue(
                ExerciseState(
                    timeLeft = activeExerciseSet.exerciseList[FIRST_ITEM].time,
                    title = activeExerciseSet.exerciseList[FIRST_ITEM].title,
                    image = activeExerciseSet.exerciseList[FIRST_ITEM].image
                )
            )
            Log.d("AAA SET", exerciseSetState.value.toString())
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
//                startSetTimer()
//                startExerciseTimer()
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
        //TODO: Extention function
        if (exerciseList.size < index) {
            result = exerciseList[index].title
        }
        return result
    }

    private fun startSetTimer() {
        setTimer = object : CountDownTimer(mutableExerciseSetState.value?.timeLeft ?: 0, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mutableExerciseSetState.value?.let {
                    mutableExerciseSetState.postValue(it.copy(timeLeft = millisUntilFinished))
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
        mutableExerciseState.value?.let { exerciseState ->
            exerciseTimer = object : CountDownTimer(exerciseState.timeLeft, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    mutableExerciseState.postValue(exerciseState.copy(timeLeft = millisUntilFinished))
                }

                override fun onFinish() {
                    exerciseTimer.cancel()
                    mutableExerciseSetState.value?.let { exerciseSetState ->
                        mutableExerciseSetState.postValue(
                            exerciseSetState.copy(
                                currentExerciseIndex = exerciseSetState.currentExerciseIndex + 1,
                                nextName = getNextExerciseName(
                                    exerciseSetState.exerciseList,
                                    exerciseSetState.currentExerciseIndex
                                ),
                                exercisesLeft = exerciseSetState.currentExerciseIndex
                            )
                        )

//                mutableExerciseState.value.let {
//                    mutableExerciseState.postValue(
//                        it.copy(
//                            timeLeft = mutableExerciseSetState.value.exerciseList[FIRST_ITEM].time,
//                            title = mutableExerciseSetState.value.exerciseList[FIRST_ITEM].title,
//                            image = mutableExerciseSetState.value.exerciseList[FIRST_ITEM].image
//                        )
//                    )
//                }

                        startExerciseTimer()
                    }
                }
            }.start()
        }
    }

    private fun stopExerciseTimer() {
        exerciseTimer.cancel()
    }
}
