package com.piotr.practicepad.exercise

import com.piotr.practicepad.R

data class ExerciseState(
    val setName: String = "",
    val setTimeLeft: Long = 0L,
    val exerciseImage: Int = R.drawable.empty,
    val exerciseName: String = "",
    val nextExerciseName: String = "",
    val exercisesLeft: Pair<Int, Int> = Pair(0, 0),
    val currentExerciseIndex: Int = 0,
    val exerciseList: ArrayList<Exercise> = arrayListOf(),
    val exerciseTimeLeft: Long = 0L
)