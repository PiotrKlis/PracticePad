package com.piotr.practicepad.ui.main

import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSetData

class ExerciseDataRepositoryImpl : ExerciseDataRepository {
    override fun getActiveExerciseSet(id: Int?): ExerciseSetData {
        for (exerciseData in ExerciseSetData.values()) {
            if (exerciseData.id == id) {
                return exerciseData
            }
        }
        return ExerciseSetData.BEGINNER
    }

    override fun getAllExerciseSets(): Array<ExerciseSetData> {
        return ExerciseSetData.values()
    }
}