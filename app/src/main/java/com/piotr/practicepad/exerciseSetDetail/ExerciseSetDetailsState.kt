package com.piotr.practicepad.exerciseSetDetail

import com.piotr.practicepad.R
import com.piotr.practicepad.exercise.Exercise

data class ExerciseSetDetailsState(
    val name: String = "",
    val tempo: Int = 0,
    val exerciseDetailsList: List<Exercise> = emptyList()
)

data class ExerciseDetails(
    val name: String = "",
    val time: Long = 0L,
    val image: Int = R.drawable.single_16th
)