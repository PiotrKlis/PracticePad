package com.piotr.practicepad.data.repository

import com.piotr.practicepad.ExerciseList.ExerciseSet

interface DataRepository {
    fun getAllExerciseSets(): List<ExerciseSet>
    fun getActiveExerciseSet(): ExerciseSet
}