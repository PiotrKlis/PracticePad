package com.piotr.practicepad.ui.main.data

import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet

interface DataRepository {
    fun getAllExerciseSets(): List<ExerciseSet>
    fun getActiveExerciseSet(id: Int?): ExerciseSet
}