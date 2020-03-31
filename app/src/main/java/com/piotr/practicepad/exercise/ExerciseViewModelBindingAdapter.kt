package com.piotr.practicepad.exercise

import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.piotr.practicepad.R
import com.piotr.practicepad.exercise.ExerciseViewModel.State
import com.piotr.practicepad.extensions.milisToMinutes
import com.piotr.practicepad.extensions.milisToSeconds

object ExerciseViewModelBindingAdapter {
    @JvmStatic
    @BindingAdapter("setImageButton")
    fun setImageButton(view: ImageButton, isTimerOn: State) {
        when (isTimerOn) {
            State.ON -> view.setBackgroundResource(R.drawable.ic_pause_circle_filled_black_24dp)
            State.OFF -> view.setBackgroundResource(R.drawable.ic_play_circle_filled_black_24dp)
            State.RESTART -> view.setBackgroundResource(R.drawable.ic_replay_black_24dp)
        }
    }

    @JvmStatic
    @BindingAdapter("exercisesLeft")
    fun exercisesDone(view: TextView, exercisesLeft: Pair<Int, Int>) {
        view.text = "${exercisesLeft.first + 1}/${exercisesLeft.second}"
    }

    @JvmStatic
    @BindingAdapter("timeLeft")
    fun timeLeft(view: TextView, time: Long) {
        view.text = String.format("%02d:%02d", time.milisToMinutes(), time.milisToSeconds())
    }
}
