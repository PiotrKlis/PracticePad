package com.piotr.practicepad.ui.main

import com.piotr.practicepad.ui.main.Exercise.ExerciseData
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSetData

interface ExerciseDataRepository {
    fun getAllExerciseSets(): Array<ExerciseSetData>
    fun getActiveExerciseSet(id: Int): ExerciseSetData

}