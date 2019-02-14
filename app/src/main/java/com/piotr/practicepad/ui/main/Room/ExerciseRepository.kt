package com.piotr.practicepad.ui.main.Room

import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSetData

class ExerciseRepository {

    fun getExerciseSetById(exerciseSetId: Int?): ExerciseSetData? {
        for (exerciseData in ExerciseSetData.values()) {
            if (exerciseData.id == exerciseSetId) {
                return exerciseData
            }
        }
        return ExerciseSetData.BEGINNER
    }
}
