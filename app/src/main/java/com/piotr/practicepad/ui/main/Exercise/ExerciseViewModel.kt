package com.piotr.practicepad.ui.main

import android.os.CountDownTimer
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.Exercise.Exercise
import com.piotr.practicepad.ui.main.Exercise.ExerciseSetState
import com.piotr.practicepad.ui.main.Exercise.ExerciseState
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet
import com.piotr.practicepad.ui.main.data.repository.ExerciseDataRepository
import com.piotr.practicepad.ui.main.utils.Event

private const val FIRST_ITEM = 0

class ExerciseViewModel : ViewModel() {
    var setTimer: CountDownTimer? = null
    var exerciseTimer: CountDownTimer? = null

    private val mutableExerciseState = MutableLiveData<ExerciseState>()
    private val mutableExerciseEvent = MutableLiveData<Event<ExerciseState>>()

    private val mutableExerciseSetState = MutableLiveData<ExerciseSetState>()
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
    private lateinit var exerciseSet: ExerciseSet
    enum class State {
        ON, OFF, RESTART
    }

    init {
        mutableExerciseSetState.value = ExerciseSetState()
        mutableExerciseState.value = ExerciseState()
    }

    fun fetchData() {
        exerciseSet = ExerciseDataRepository().getActiveExerciseSet()

        ExerciseDataRepository().getActiveExerciseSet().let {
            mutableExerciseSetState.value =
                ExerciseSetState(
                    it.title,
                    getOverallTime(it.exerciseList),
                    getNextExerciseName(it.exerciseList, FIRST_ITEM),
                    getExercisesDone(it.exerciseList)
                )

            mutableExerciseState.value =
                ExerciseState(
                    it.exerciseList[FIRST_ITEM].time,
                    it.exerciseList[FIRST_ITEM].title,
                    it.exerciseList[FIRST_ITEM].image
                )
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

    //TODO: Binding adapter?
    private fun getExercisesDone(exerciseList: ArrayList<Exercise>): String {
        val index = mutableExerciseSetState.value!!.currentExerciseIndex

        return if (exerciseList.size < index) {
            "${mutableExerciseSetState.value?.currentExerciseIndex?.plus(1)}/${exerciseList.size}"
        } else {
            "$mutableExerciseSetState.value?.currentExerciseIndex?/${exerciseList.size}"
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
                mutableExerciseSetState.value?.let { mutableExerciseSetState.postValue(it.copy(currentExerciseIndex = 0)) }
                fetchData()
                startSetTimer(exerciseSet.exerciseList[mutableExerciseSetState.value?.currentExerciseIndex!!].time)
                startExerciseTimer(exerciseSet.exerciseList[mutableExerciseSetState.value?.currentExerciseIndex!!].time)
            }
        }
    }

    private fun startSetTimer(time: Long) {
        setTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mutableExerciseSetState.value?.let { exerciseSetState -> exerciseSetState. mutableExerciseSetState.postValue(it.copy()) }
                overallTime.set(millisUntilFinished)
                currentOverallTime = millisUntilFinished
            }

            override fun onFinish() {
                setTimer?.cancel()
                isTimerOn.set(State.RESTART)
            }
        }.start()
    }

    private fun stopSetTimer() {
        setTimer?.cancel()
    }

    private fun stopExerciseTimer() {
        exerciseTimer?.cancel()
    }





    private fun startExerciseTimer(time: Long) {
        exerciseTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentExerciseTimeLeft.set(millisUntilFinished)
                currentExerciseTime = millisUntilFinished
            }

            override fun onFinish() {
                exerciseTimer?.cancel()
                currentExerciseNumber += 1
                runExerciseTimer()
            }
        }.start()
    }



}
