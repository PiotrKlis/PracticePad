package com.piotr.practicepad.data.repository

import com.piotr.practicepad.ExerciseList.ExerciseSet
import com.piotr.practicepad.data.db.ExerciseSetData
import com.piotr.practicepad.data.db.SharedPrefs

class ExerciseDataRepository : DataRepository {
    private val mapper: DataMapper = DataMapper()

    override fun getActiveExerciseSet(): ExerciseSet {
        val id = SharedPrefs.getActiveSet()

        return ExerciseSetData
            .values()
            .filter { it.id == id }
            .map { mapper.mapToExerciseSet(it) }
            .first()
    }

    override fun getAllExerciseSets(): List<ExerciseSet> {
        return ExerciseSetData
            .values()
            .let { mapper.mapToExerciseSetList(it) }
    }
}