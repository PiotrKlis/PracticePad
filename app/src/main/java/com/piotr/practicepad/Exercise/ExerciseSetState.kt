package com.piotr.practicepad.Exercise

data class ExerciseSetState(
    val name: String = "",
    val timeLeft: String = "",
    val nextName: String = "",
    val exercisesLeft: Int = 0,
    val currentExerciseIndex: Int = 0
)