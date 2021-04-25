package com.piotr.practicepad.views.exerciseSetList

import com.piotr.practicepad.views.exercise.Exercise

data class ExerciseSet(
    val id: Int = 0,
    val title: String = "",
    val tempo: Long = 0L,
    val exercises: List<Exercise> = listOf()
) {
    fun shouldStartNextExercise(position: Int): Boolean = position < exercises.size
}
