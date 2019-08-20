package com.piotr.practicepad.Exercise

import androidx.databinding.BindingAdapter
import android.view.View
import com.piotr.practicepad.R
import com.piotr.practicepad.Exercise.ExerciseViewModel.State

object ExerciseViewModelBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:background")
    fun setImageButton(view: View, isTimerOn: State) {
        when (isTimerOn) {
            State.ON -> view.setBackgroundResource(R.drawable.ic_pause_circle_filled_black_24dp)
            State.OFF -> view.setBackgroundResource(R.drawable.ic_play_circle_filled_black_24dp)
            State.RESTART-> view.setBackgroundColor(R.drawable.ic_home_black_24dp)
        }
    }

    @JvmStatic
    @BindingAdapter("android:background")
    fun setImage(view: View, currentExerciseImage: Int) {
        view.setBackgroundResource(currentExerciseImage)
    }
}