package com.piotr.practicepad.ui.main.data.repository

import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet

interface DataRepository {
    fun getAllExerciseSets(): List<ExerciseSet>
    fun getActiveExerciseSet(): ExerciseSet
}