package com.piotr.practicepad.views.exercise

sealed class ExerciseEvent {
    data class NextExercise(val position: Int) : ExerciseEvent()
    object SetEnded : ExerciseEvent()
}
