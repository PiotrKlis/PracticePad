package com.piotr.practicepad.exerciseList

import com.piotr.practicepad.exercise.Exercise

data class ExerciseSet(val id: Int, val title: String, val exerciseList: ArrayList<Exercise>)
