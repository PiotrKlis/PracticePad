package com.piotr.practicepad.ui.main

import android.arch.lifecycle.ViewModel
import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.os.CountDownTimer
import android.view.View
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.Exercise.Exercise
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet
import com.piotr.practicepad.ui.main.data.repository.ExerciseDataRepository
import com.piotr.practicepad.ui.main.utils.Helper

class ExerciseViewModel : ViewModel() {
    var overallSetTimer: CountDownTimer? = null
    var exerciseTimer: CountDownTimer? = null
    
    lateinit var exerciseSet: ExerciseSet
    var exerciseList = ArrayList<Exercise>()

    val overallTime = ObservableField<String>()
    val exerciseSetName = ObservableField<String>()
    val exercisesDone = ObservableField<String>()
    val nextExerciseName = ObservableField<String>()

    val currentExerciseName = ObservableField<String>()
    val currentExerciseTimeLeft = ObservableField<String>()
    val currentExerciseImage = ObservableField<Int>()

    val isTimerOn = ObservableBoolean(false)
    val refreshViewEvent = SingleLiveEvent<Void>()

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

    private fun updateExerciseData() {
        exercisesDone.set(getExercisesDone())
        nextExerciseName.set(getNextExerciseName())
        currentExerciseName.set(getCurrentExerciseName())
        currentExerciseImage.set(getImage())
    }

    private fun getExerciseSetName(): String {
        return exerciseSet.title
    }

    private fun getImage(): Int {
        return exerciseList[currentExerciseNumber].image
    }

    private fun getOverallTime(): String {
        var seconds: Long = 0
        seconds = sumSeconds(seconds)
        currentOverallTime = Helper.secondsToMiliseconds(seconds)
        return Helper.convertIntoMinutesSeoconds(seconds)
    }


    private fun getTimeLeft(): String {
        currentExerciseTime = exerciseList[currentExerciseNumber].time
        return Helper.convertIntoMinutesSeoconds(currentOverallTime)
    }

    private fun fasfsa(i: Exercise): Long {
        val value = i.time
        return value
    }

    private fun getCurrentExerciseName(): String {
        return exerciseList[currentExerciseNumber].title
    }

    fun runTimer() {
        if (isTimerOn.get()) {
            isTimerOn.set(false)
            stopSetTimer()
            stopExerciseTimer()
        } else {
            isTimerOn.set(true)
            startSetTimer(currentOverallTime)
            startExerciseTimer(currentExerciseTime)
        }
    }

    fun runExerciseTimer() {
        if (currentExerciseNumber < exerciseList.size){
            val time = exerciseList[currentExerciseNumber].time
            updateExerciseData()
            startExerciseTimer(time)
        } else {
            currentExerciseName.set("All done, congratulations!")
        }
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


    companion object {
        @JvmStatic
        @BindingAdapter("android:background")
        fun setImageButton(view: View, isTimerOn: Boolean) {
            if (isTimerOn) {
                view.setBackgroundResource(R.drawable.ic_pause_circle_filled_black_24dp)
            } else {
                view.setBackgroundResource(R.drawable.ic_play_circle_filled_black_24dp)
            }
        }

        @JvmStatic
        @BindingAdapter("android:background")
        fun setImage(view: View, currentExerciseImage: Int) {
            view.setBackgroundResource(currentExerciseImage)
        }
    }

    private fun startSetTimer(time: Long) {
        overallSetTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                overallTime.set(Helper.convertIntoMinutesSeoconds(millisUntilFinished))
                currentOverallTime = millisUntilFinished
            }

            override fun onFinish() {
                overallSetTimer?.cancel()
                isTimerOn.set(false)
            }
        }.start()
    }

    private fun stopSetTimer() {
        overallSetTimer?.cancel()
    }

    private fun startExerciseTimer(time: Long) {
        exerciseTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentExerciseTimeLeft.set(Helper.convertIntoMinutesSeoconds(millisUntilFinished))
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

    private fun sumSeconds(seconds: Long): Long {
        var seconds1 = seconds
        for (i in exerciseList) {
            val value = fasfsa(i)
            seconds1 += value
        }
        return seconds1
    }

}
