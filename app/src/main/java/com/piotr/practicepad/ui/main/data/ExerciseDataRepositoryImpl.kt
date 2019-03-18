package com.piotr.practicepad.ui.main.data

import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet

class ExerciseDataRepositoryImpl : DataRepository {
    val mapper: DataMapper = DataMapper()

    override fun getActiveExerciseSet(id: Int?): ExerciseSet {

        for (exerciseData in ExerciseSetData.values()) {
            if (exerciseData.id == id) {
                return mapper.mapToExerciseSet(exerciseData)
            }
        }
        return mapper.mapToExerciseSet(ExerciseSetData.BEGINNER)
    }

    override fun getAllExerciseSets(): List<ExerciseSet> {
        return mapper.mapToExerciseSetList(ExerciseSetData.values())
    }
}