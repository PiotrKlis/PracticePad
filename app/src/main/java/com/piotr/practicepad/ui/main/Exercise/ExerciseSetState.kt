package com.piotr.practicepad.ui.main.Exercise

data class ExerciseSetState(
    val name: String = "",
    val timeLeft: Long = 0L,
    val nextName: String = "",
    val exercisesLeft: String = "",
    val currentExerciseIndex: Int = 0
)