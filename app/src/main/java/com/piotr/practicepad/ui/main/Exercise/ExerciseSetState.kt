package com.piotr.practicepad.ui.main.Exercise

data class ExerciseSetState(
    val name: String = "",
    val timeLeft: String = "",
    val nextName: String = "",
    val exercisesLeft: String = "",
    val currentExerciseIndex: Int = 0
)