package com.piotr.practicepad.ui.main.ExerciseSetList

import com.piotr.practicepad.ui.main.ExerciseSet.Exercise

data class ExerciseSet(val id: Int, val title: String, val exerciseList: List<Exercise>)