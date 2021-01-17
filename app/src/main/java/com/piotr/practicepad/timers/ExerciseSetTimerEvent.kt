package com.piotr.practicepad.timers

sealed class ExerciseSetTimerEvent {
    object ExerciseSetEnded : ExerciseSetTimerEvent()
}
