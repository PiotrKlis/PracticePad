package com.piotr.practicepad.exercise

import com.piotr.practicepad.R

data class ExerciseState(
    val setName: String = "",
    val exerciseImage: Int = R.drawable.single_16th,
    val exerciseName: String = "",
    val nextExerciseName: String = "",
    val exercisesLeft: Pair<Int, Int> = Pair(0, 0),
    val currentExerciseIndex: Int = 0,
    val exerciseList: List<Exercise> = listOf(),
    val tempo: Long = 0
)
