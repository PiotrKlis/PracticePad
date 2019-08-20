package com.piotr.practicepad.ExerciseList

import com.piotr.practicepad.Exercise.Exercise

data class ExerciseSet(val id: Int, val title: String, val exerciseList: ArrayList<Exercise>)
