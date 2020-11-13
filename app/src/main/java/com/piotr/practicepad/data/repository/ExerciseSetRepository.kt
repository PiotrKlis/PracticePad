package com.piotr.practicepad.data.repository

import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.exerciseList.ExerciseSet
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class ExerciseSetRepository @Inject constructor(
    private val sharedPrefs: SharedPrefs,
    private val database: PracticePadRoomDatabase,
    private val exerciseSetEntityMapper: ExerciseSetEntityMapper
) {
    @OptIn(ExperimentalTime::class)
    suspend fun getActiveSet(): ExerciseSet {
        return exerciseSetEntityMapper.map(database.exerciseSetDao().getSetFor(sharedPrefs.getActiveSetId()))
//        val test = database.exerciseSetDao().getSetFor(sharedPrefs.getActiveSetId())
//        return ExerciseSet()
    }

    @OptIn(ExperimentalTime::class)
    suspend fun getAll(): List<ExerciseSet> =
        exerciseSetEntityMapper.map(database.exerciseSetDao().getAll())

    @OptIn(ExperimentalTime::class)
    suspend fun getSetForId(id: Int): ExerciseSet =
        exerciseSetEntityMapper.map(database.exerciseSetDao().getSetFor(id))
}
