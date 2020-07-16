package com.piotr.practicepad.exercise

data class ExerciseState(
    val setName: String = "",
    val exerciseImage: Int? = null,
    val exerciseName: String = "",
    val nextExerciseName: String = "",
    val exercisesLeft: Pair<Int, Int> = Pair(0, 0),
    val currentExerciseIndex: Int = 0,
    val exerciseList: List<Exercise> = listOf(),
    val tempo: Long = 0
)
