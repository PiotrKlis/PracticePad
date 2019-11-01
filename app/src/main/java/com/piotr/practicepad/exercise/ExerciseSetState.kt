package com.piotr.practicepad.exercise

data class ExerciseSetState(
    val name: String = "",
    val timeLeft: Long = 0L,
    val nextName: String = "",
    val exercisesLeft: Int = 0,
    val currentExerciseIndex: Int = 0,
    val exerciseList: ArrayList<Exercise> = arrayListOf()
)