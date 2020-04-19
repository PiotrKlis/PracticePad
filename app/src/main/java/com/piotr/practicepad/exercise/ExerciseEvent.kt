package com.piotr.practicepad.exercise

import com.piotr.practicepad.PracticeState

sealed class ExerciseEvent {
    data class PowerClick(val state: PracticeState.State) : ExerciseEvent()
    object OnPause : ExerciseEvent()
}
