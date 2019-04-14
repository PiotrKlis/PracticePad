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
    var overallTimer: CountDownTimer? = null
    var currentExerciseTimer: CountDownTimer? = null
    lateinit var exerciseSet: ExerciseSet
    lateinit var exerciseSetTitle: String
    var currentExerciseNumber: Int = 0

    var exerciseList = ArrayList<Exercise>()

    val overallTime = ObservableField<String>()
    val exercisesDone = ObservableField<String>()
    val nextExerciseName = ObservableField<String>()
    val currentExerciseName = ObservableField<String>()
    val timeleft = ObservableField<String>()
    val currentExerciseImage = ObservableField<Int>()
    val isTimerOn = ObservableBoolean(false)
    val refreshViewEvent = SingleLiveEvent<Void>()

    var currentOverallTime: Long = 0
    var currentExerciseTime: Long = 0

    fun fetchCurrentExerciseSet() {
        exerciseSet = ExerciseDataRepository().getActiveExerciseSet()
        exerciseList = exerciseSet.exerciseList
    }

    fun renderData() {
        overallTime.set(getOverallTime())
        exercisesDone.set(getExercisesDone())
        nextExerciseName.set(getNextExerciseName())
        currentExerciseName.set(getCurrentExerciseName())
        timeleft.set(getTimeLeft())
        currentExerciseImage.set(getImage())
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

    private fun getExercisesDone(): String {
        val current = currentExerciseNumber + 1
        return "$current/${getListSize()}"
    }


    private fun getNextExerciseName(): String {
        return if (currentExerciseNumber + 1 <= exerciseList.size) {
            exerciseList[currentExerciseNumber].title
        } else {
            ""
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
        overallTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                overallTime.set(Helper.convertIntoMinutesSeoconds(millisUntilFinished))
                currentOverallTime = millisUntilFinished
            }

            override fun onFinish() {
                overallTimer?.cancel()
            }
        }.start()
    }

    private fun stopSetTimer() {
        overallTimer?.cancel()
    }

    private fun startExerciseTimer(time: Long) {
        currentExerciseTimer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeleft.set(Helper.convertIntoMinutesSeoconds(millisUntilFinished))
                currentExerciseTime = millisUntilFinished
            }

            override fun onFinish() {
                overallTimer?.cancel()
            }
        }.start()
    }

    private fun stopExerciseTimer() {
        currentExerciseTimer?.cancel()
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
