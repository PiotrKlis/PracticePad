package com.piotr.practicepad.ui.main.data

import android.arch.lifecycle.ViewModel
import android.databinding.*
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.Exercise.Exercise
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet
import com.piotr.practicepad.ui.main.utils.Helper

class ExerciseViewModel : ViewModel() {

    lateinit var exerciseSet: ExerciseSet
    lateinit var exerciseSetTitle: String
    val exerciseList = ArrayList<Exercise>()
    val overallTime = ObservableField<String>()
    val exercisesDone = ObservableField<String>()
    val nextExerciseName = ObservableField<String>()
    val currentExerciseName = ObservableField<String>()
    val currentExerciseTimeLeft = ObservableField<String>()
    val currentExerciseImage = ObservableField<Drawable>()
    val isTimerOn = ObservableBoolean(false)

    fun fetchCurrentExerciseSet() {
        exerciseSet = ExerciseDataRepository().getActiveExerciseSet()

    }

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
