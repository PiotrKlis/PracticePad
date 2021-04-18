package com.piotr.practicepad.views.exerciseSet

import com.piotr.practicepad.views.exercise.Exercise

data class ExerciseSetState(
    val id: Int = 0,
    val name: String = "",
    val tempo: Long = 90,
    val exerciseDetailsList: MutableList<Exercise> = mutableListOf()
)
