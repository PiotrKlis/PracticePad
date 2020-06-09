package com.piotr.practicepad.exerciseSetDetail

import com.piotr.practicepad.exercise.Exercise

data class ExerciseSetDetailsState(
    val name: String = "",
    val tempo: Int = 0,
    val exerciseDetailsList: List<Exercise> = emptyList()
)
