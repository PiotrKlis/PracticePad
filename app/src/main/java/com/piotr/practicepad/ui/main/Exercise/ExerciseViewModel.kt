package com.piotr.practicepad.ui.main

import android.os.CountDownTimer
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.Exercise.Exercise
import com.piotr.practicepad.ui.main.ExerciseList.DummyData
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet
import com.piotr.practicepad.ui.main.data.repository.ExerciseDataRepository

class ExerciseViewModel : ViewModel() {
    var setTimer: CountDownTimer? = null
    var exerciseTimer: CountDownTimer? = null

    // Current Exercise
    // Current Exercise Set
    // Timer

    var exerciseSet: ExerciseSet = DummyData.getEmptyExerciseSet()
    var exerciseList = ArrayList<Exercise>()

    fun isExerciseNumberInSize(): Boolean = currentExerciseNumber < exerciseList.size
    fun isNextExerciseNumberInSize(): Boolean = currentExerciseNumber + 1 < exerciseList.size

    val exerciseSetName = ObservableField<String>()
    val exercisesDone = ObservableField<String>()
    val overallTime = ObservableField<Long>()
    var currentOverallTime: Long = 0

    val nextExerciseName = ObservableField<String>()
    val currentExerciseName = ObservableField<String>()
    val currentExerciseTimeLeft = ObservableField<Long>()
    var currentExerciseNumber: Int = 0
    var currentExerciseTime: Long = 0

    val currentExerciseImage = ObservableField<Int>()

    val isTimerOn = ObservableField(State.OFF)

    enum class State {
        ON, OFF, RESTART
    }

    fun fetchData() {
        exerciseSet = ExerciseDataRepository().getActiveExerciseSet()
        exerciseList = exerciseSet.exerciseList
    }

    fun renderData() {
        exerciseSetName.set(getExerciseSetName())
        overallTime.set(getOverallTime())
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
        nextExerciseName.set(getNextExerciseName())
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

    private fun getExercisesDone(): String {
        return if (isExerciseNumberInSize()) {
            "${currentExerciseNumber+1}/${getListSize()}"
        } else {
            "$currentExerciseNumber/${getListSize()}"
        }
    }


    private fun getNextExerciseName(): String {
        return if (isNextExerciseNumberInSize()) {
            exerciseList[currentExerciseNumber + 1].title
        } else {
            "last one"
        }
    }

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

    private fun getOverallTime(): Long {
        val seconds = sumSeconds()
        currentOverallTime = seconds
        return seconds
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
            "Last Exercise"
        }
    }

    private fun sumSeconds(): Long {
        var seconds: Long = 0
        for (i in exerciseList) {
            val value = i.time
            seconds += value
        }
        return seconds
    }
}
