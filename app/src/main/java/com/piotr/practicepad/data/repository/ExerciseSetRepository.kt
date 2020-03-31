package com.piotr.practicepad.data.repository

import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.data.db.ExerciseSetData
import com.piotr.practicepad.data.db.ActiveSetSharedPrefs
import javax.inject.Inject

class ExerciseSetRepository @Inject constructor(private val activeSetSharedPrefs: ActiveSetSharedPrefs) {
    private val setListMapper: ExerciseSetListMapper = ExerciseSetListMapper()
    private val exerciseSetMapper: ExerciseSetMapper = ExerciseSetMapper()

    fun getActiveSet(): ExerciseSet {
        val id = activeSetSharedPrefs.getActiveSetId()
        return ExerciseSetData
            .values()
            .find { it.id == id }
            .let { exerciseSetData -> exerciseSetMapper.map(exerciseSetData ?: ExerciseSetData.EVERY_DAY) }
    }

    fun getAll(): List<ExerciseSet> {
        return ExerciseSetData
            .values()
            .let { setListMapper.map(it) }
    }
}
