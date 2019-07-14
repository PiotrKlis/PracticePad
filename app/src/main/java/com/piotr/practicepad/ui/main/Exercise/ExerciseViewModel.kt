package com.piotr.practicepad.ui.main

import android.os.CountDownTimer
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.Exercise.Exercise
import com.piotr.practicepad.ui.main.Exercise.ExerciseSetState
import com.piotr.practicepad.ui.main.Exercise.ExerciseState
import com.piotr.practicepad.ui.main.data.repository.ExerciseDataRepository
import com.piotr.practicepad.ui.main.utils.Event

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

    enum class State {
        ON, OFF, RESTART
    }

    init {
        mutableExerciseSetState.value = ExerciseSetState()
        mutableExerciseState.value = ExerciseState()
    }

    fun fetchData() {
        ExerciseDataRepository().getActiveExerciseSet().let {
            mutableExerciseSetState.value =
                ExerciseSetState(
                    it.title,
                    getOverallTime(it.exerciseList),
                    getNextExerciseName(it.exerciseList, 1),
                    getExercisesDone()
                )
        }
        exerciseList = exerciseSet.exerciseStateList
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
    private fun getExercisesDone(): String {
        return if (isExerciseNumberInSize()) {
            "${currentExerciseNumber + 1}/${getListSize()}"
        } else {
            "$currentExerciseNumber/${getListSize()}"
        }
    }

    fun renderData() {
        exerciseSetName.set(getExerciseSetName())
        overallTime.set(getOverallTime(it.exerciseStateList))
        currentExerciseTimeLeft.set(getTimeLeft())
        updateExerciseData()
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
                startSetTimer(currentOverallTime)
                startExerciseTimer(currentExerciseTime)
            }
            State.RESTART -> {
                isTimerOn.set(State.ON)
                currentExerciseNumber = 0
                renderData()
                startSetTimer(currentOverallTime)
                startExerciseTimer(currentExerciseTime)
            }
        }
    }

    private fun updateExerciseData() {
        exercisesDone.set(getExercisesDone())
        nextExerciseName.set(getNextExerciseName(it.exerciseList))
        currentExerciseName.set(getCurrentExerciseName())
        currentExerciseImage.set(getImage())
    }

    private fun runExerciseTimer() {
        if (currentExerciseNumber < exerciseList.size) {
            val time = exerciseList[currentExerciseNumber].time
            updateExerciseData()
            startExerciseTimer(time)
        } else {
            currentExerciseName.set("All done, congratulations!")
        }
    }

    private fun startSetTimer(time: Long) {
        setTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
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

    private fun stopExerciseTimer() {
        exerciseTimer?.cancel()
    }

    private fun isExerciseNumberInSize(): Boolean =
        exerciseSetState.value.let { exerciseSetState -> } currentExerciseNumber < exerciseList . size
                private

    fun isNextExerciseNumberInSize(): Boolean = currentExerciseNumber + 1 < exerciseList.size


    private fun getListSize(): Int {
        return exerciseList.size
    }

    private fun getExerciseSetName(): String {
        return exerciseSet.title
    }

    private fun getImage(): Int {
        return if (isExerciseNumberInSize()) {
            exerciseList[currentExerciseNumber].image
        } else {
            exerciseList[currentExerciseNumber - 1].image
        }
    }

    private fun getTimeLeft(): Long {
        return if (isExerciseNumberInSize()) {
            exerciseList[currentExerciseNumber].time
        } else {
            0
        }
    }

    private fun getCurrentExerciseName(): String {
        return if (isExerciseNumberInSize()) {
            exerciseList[currentExerciseNumber].title
        } else {
            "Last ExerciseState"
        }
    }
}
