package com.piotr.practicepad.data.repository

import com.piotr.practicepad.data.db.ExerciseSetData
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.exerciseList.ExerciseSet
import javax.inject.Inject

class ExerciseSetRepository @Inject constructor(private val sharedPrefs: SharedPrefs) {
    private val setListMapper: ExerciseSetListMapper = ExerciseSetListMapper()
    private val exerciseSetMapper: ExerciseSetMapper = ExerciseSetMapper()

    fun getActiveSet(): ExerciseSet {
        val id = sharedPrefs.getActiveSetId()
        return ExerciseSetData
            .values()
            .find { it.id == id }
            .let { exerciseSetData ->
                exerciseSetMapper.map(
                    exerciseSetData ?: ExerciseSetData.EVERY_DAY_WORKOUT
                )
            }
    }

    fun getAll(): List<ExerciseSet> {
        return ExerciseSetData
            .values()
            .let { setListMapper.map(it) }
    }

    fun getSetForId(id: Int): ExerciseSet = ExerciseSetData
        .values()
        .find { it.id == id }
        .let { exerciseSetData ->
            exerciseSetMapper.map(
                exerciseSetData ?: ExerciseSetData.EVERY_DAY_WORKOUT
            )
        }

}
