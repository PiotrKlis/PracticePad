package com.piotr.practicepad.views.exercise

import com.piotr.practicepad.R

data class Exercise(
    val id: Int = 0,
    val time: Long = 0L,
    val title: String = "",
    val image: Int = R.drawable.empty
)
