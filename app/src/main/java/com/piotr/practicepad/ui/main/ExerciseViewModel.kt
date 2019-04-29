package com.piotr.practicepad.ui.main

import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import android.os.CountDownTimer
import com.piotr.practicepad.ui.main.Exercise.Exercise
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet
import com.piotr.practicepad.ui.main.data.repository.ExerciseDataRepository

class ExerciseViewModel : ViewModel() {
    var overallSetTimer: CountDownTimer? = null
    var exerciseTimer: CountDownTimer? = null

    lateinit var exerciseSet: ExerciseSet
    var exerciseList = ArrayList<Exercise>()

    val overallTime = ObservableField<Long>()
    val exerciseSetName = ObservableField<String>()
    val exercisesDone = ObservableField<String>()
    val nextExerciseName = ObservableField<String>()

    val currentExerciseName = ObservableField<String>()
    val currentExerciseTimeLeft = ObservableField<Long>()
    val currentExerciseImage = ObservableField<Int>()

    val isTimerOn = ObservableField<State>(State.OFF)

    enum class State {
        ON, OFF, RESTART
    }

    var currentExerciseNumber: Int = 0
    var currentOverallTime: Long = 0
    var currentExerciseTime: Long = 0

    fun fetchCurrentExerciseSet() {
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
        overallSetTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                overallTime.set(millisUntilFinished)
                currentOverallTime = millisUntilFinished
            }

            override fun onFinish() {
                overallSetTimer?.cancel()
                isTimerOn.set(State.RESTART)
            }
        }.start()
    }

    private fun stopSetTimer() {
        overallSetTimer?.cancel()
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
        val current = currentExerciseNumber + 1
        return "$current/${getListSize()}"
    }


    private fun getNextExerciseName(): String {
        return if (currentExerciseNumber + 1 < exerciseList.size) {
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
        return exerciseList[currentExerciseNumber].image
    }

    private fun getOverallTime(): Long {
        val seconds = sumSeconds()
        currentOverallTime = seconds
        return seconds
    }

    private fun getTimeLeft(): Long {
        currentExerciseTime = exerciseList[currentExerciseNumber].time
        return currentExerciseTime
    }

    private fun getCurrentExerciseName(): String {
        return exerciseList[currentExerciseNumber].title
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
