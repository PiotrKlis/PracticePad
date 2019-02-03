package com.piotr.practicepad.ui.main

import com.piotr.practicepad.ui.main.Exercise.ExerciseData
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSetData

class ExerciseDataRepositoryImpl : ExerciseDataRepository {
    override fun getAllExerciseSets(): Array<ExerciseSetData> {
        return ExerciseSetData.values()
    }

    override fun getActiveExerciseSet(id: Int): ExerciseSetData {
        return ExerciseSetData.values().get(id)
    }
}