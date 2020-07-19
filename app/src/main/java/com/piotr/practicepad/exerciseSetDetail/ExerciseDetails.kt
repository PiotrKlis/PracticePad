package com.piotr.practicepad.exerciseSetDetail

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

object ExerciseDetailsBindingAdapter {
    @JvmStatic
    @BindingAdapter("bind:position", "bind:setSize")
    fun exercisesDone(view: AppCompatImageView, position: Int, setSize: Int) {
        view.isVisible = position == 0 || position == setSize - 1
    }
}