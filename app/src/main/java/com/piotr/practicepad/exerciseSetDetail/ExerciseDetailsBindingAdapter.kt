package com.piotr.practicepad.exerciseSetDetail

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.piotr.practicepad.extensions.isNotVisible

object ExerciseDetailsBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["bind:position", "bind:setSize"], requireAll = false)
    fun exercisesDone(view: AppCompatImageView, position: Int, setSize: Int) {
        view.isNotVisible = position == 0 || position == setSize - 1
    }
}
