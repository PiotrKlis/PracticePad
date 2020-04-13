package com.piotr.practicepad.exercise

sealed class ExerciseEvent {
    data class PowerClick(val state: ExerciseViewModel.Statetos, val time: Long = 0) : ExerciseEvent()
}
