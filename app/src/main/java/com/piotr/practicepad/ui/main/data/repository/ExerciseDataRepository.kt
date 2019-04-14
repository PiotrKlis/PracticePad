package com.piotr.practicepad.ui.main.data.repository

import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet
import com.piotr.practicepad.ui.main.data.db.SharedPrefs
import com.piotr.practicepad.ui.main.data.db.ExerciseSetData

class ExerciseDataRepository : DataRepository {
    val mapper: DataMapper = DataMapper()

    override fun getActiveExerciseSet(): ExerciseSet {
        val id = SharedPrefs.getActiveSet()
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