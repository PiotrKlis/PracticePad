package com.piotr.practicepad.exerciseSetDetail

import com.piotr.practicepad.R

data class ExerciseSetDetailsState(
    val name: String = "",
    val tempo: Int = 0,
    val exerciseDetailsList: List<ExerciseDetails> = emptyList()
)

data class ExerciseDetails(
    val name: String = "",
    val time: Long = 0L,
    val image: Int = R.drawable.single_16th
)