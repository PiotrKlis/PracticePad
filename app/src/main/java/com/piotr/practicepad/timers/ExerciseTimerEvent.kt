package com.piotr.practicepad.timers

sealed class ExerciseTimerEvent {
    object ExerciseSetEnded : ExerciseTimerEvent()
}
