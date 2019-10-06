package com.piotr.practicepad.ui.main.Exercise

import com.piotr.practicepad.R

data class ExerciseState(
    val timeLeft: Long = 0L,
    val title: String = "",
    val image: Int = R.drawable.empty
)