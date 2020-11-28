package com.piotr.practicepad.exerciseSetDetail

import com.piotr.practicepad.exercise.Exercise

data class ExerciseSetDetailsState(
    val name: String = "",
    val tempo: Long = 0,
    val exerciseDetailsList: MutableList<Exercise> = mutableListOf()
)
