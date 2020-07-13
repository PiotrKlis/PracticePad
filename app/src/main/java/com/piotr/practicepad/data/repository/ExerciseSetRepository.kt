package com.piotr.practicepad.data.repository

import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.exerciseList.ExerciseSet
import javax.inject.Inject

class ExerciseSetRepository @Inject constructor(
    private val sharedPrefs: SharedPrefs,
    private val database: PracticePadRoomDatabase,
    private val exerciseSetEntityMapper: ExerciseSetEntityMapper
) {
    suspend fun getActiveSet(): ExerciseSet {
        return exerciseSetEntityMapper.map(database.exerciseSetDao().getSetFor(sharedPrefs.getActiveSetId()))
    }

    suspend fun getAll(): List<ExerciseSet> =
        exerciseSetEntityMapper.map(database.exerciseSetDao().getAll())

    suspend fun getSetForId(id: Int): ExerciseSet =
        exerciseSetEntityMapper.map(database.exerciseSetDao().getSetFor(id))
}
