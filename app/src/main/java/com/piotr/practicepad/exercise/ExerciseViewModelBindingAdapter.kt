package com.piotr.practicepad.exercise

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.piotr.practicepad.R
import com.piotr.practicepad.exercise.ExerciseViewModel.State

private const val LAST_ONE = 1

object ExerciseViewModelBindingAdapter {
    @JvmStatic
    @BindingAdapter("setImageButton")
    fun setImageButton(view: View, isTimerOn: State) {
        when (isTimerOn) {
            State.ON -> view.setBackgroundResource(R.drawable.ic_pause_circle_filled_black_24dp)
            State.OFF -> view.setBackgroundResource(R.drawable.ic_play_circle_filled_black_24dp)
            State.RESTART -> view.setBackgroundColor(R.drawable.ic_home_black_24dp)
        }
    }

    @JvmStatic
    @BindingAdapter("exercisesDone")
    fun exercisesDone(view: TextView, exerciseSetState: ExerciseSetState) {
        view.text = if (exerciseSetState.exercisesLeft > LAST_ONE) {
            "${exerciseSetState.currentExerciseIndex.plus(1)}/${exerciseSetState.exerciseList.size}"
        } else {
            "${exerciseSetState.currentExerciseIndex}/${exerciseSetState.exerciseList.size}"
        }
    }
}