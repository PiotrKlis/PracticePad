package com.piotr.practicepad.exerciseSetDetail

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

object ExerciseSetDetailsBindingAdapter {
    @JvmStatic
    @BindingAdapter("upArrowVisibility")
    fun upArrowVisibility(view: AppCompatImageView, adapterParams: AdapterParams) {
        view.isVisible = adapterParams.position != 0
    }

    @JvmStatic
    @BindingAdapter("downArrowVisibility")
    fun downArrowVisibility(view: AppCompatImageView, adapterParams: AdapterParams) {
        view.isVisible = adapterParams.position != adapterParams.size - 1
    }
}