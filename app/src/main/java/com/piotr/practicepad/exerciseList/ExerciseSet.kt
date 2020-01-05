package com.piotr.practicepad.exerciseList

import com.piotr.practicepad.exercise.Exercise

data class ExerciseSet(
    val id: Int = 0,
    val name: String = "",
    val exerciseList: ArrayList<Exercise> = arrayListOf()
)