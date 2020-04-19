package com.piotr.practicepad.exercise

sealed class ExerciseEvent {
    data class PowerClick(val state: PracticeState.State) : ExerciseEvent()
    object OnPause : ExerciseEvent()
}
