package com.piotr.practicepad.ui.main

import android.databinding.BindingAdapter
import android.view.View
import com.piotr.practicepad.R

object ExerciseViewModelBindingAdapter {
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