package com.piotr.practicepad.views.exercise

sealed class ExerciseTimerEvent {
    object ExerciseEnded : ExerciseTimerEvent()
}
