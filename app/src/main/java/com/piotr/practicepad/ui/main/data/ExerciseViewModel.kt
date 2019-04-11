package com.piotr.practicepad.ui.main.data

import android.arch.lifecycle.ViewModel
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet
import com.piotr.practicepad.ui.main.utils.Helper

class ExerciseViewModel : ViewModel() {

    var list: ExerciseSet = ExerciseDataRepository().getActiveExerciseSet()
    var exerciseList = list.exerciseList
    var overallTimer = getOverallTime()
    var exercisesDone = convertExercisesDone()

    var currentExerciseNumber = 0
    var currentOverallTimeValue: Long = 0
    var currentExercise = exerciseList.get(currentExerciseNumber)
    var currentExerciseName = currentExercise.title
    var currentExerciseTime = Helper.convertIntoMinutesSeoconds(currentExercise.time)
    var currentExerciseImage = currentExercise.image

    var nextExerciseName = getNextExerciseName().toString()

    var isTimerOn = ObservableBoolean(false)

    fun runTimer() {
        if (isTimerOn.get()) {
            isTimerOn.set(false)
        } else {
            isTimerOn.set(true)
        }
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
    }

    private fun getOverallTime(): String {
        var seconds: Long = 0
        for (i in exerciseList) {
            val value = i.time
            seconds += value
            currentOverallTimeValue = seconds
        }
        return Helper.convertIntoMinutesSeoconds(currentOverallTimeValue)
    }

    private fun convertExercisesDone(): Any {
        val current = currentExerciseNumber + 1
        return "$current/${getListSize()}"
    }


    private fun getNextExerciseName(): Any {
        return if (currentExerciseNumber + 1 <= exerciseList.size) {
            exerciseList.get(currentExerciseNumber).title
        } else {
            ""
        }
    }

    fun getListSize(): Int {
        return exerciseList.size
    }
}
