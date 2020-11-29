package com.piotr.practicepad.data.repository

import android.util.Log
import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.exerciseList.ExerciseSet
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class ExerciseSetRepository @Inject constructor(
    private val sharedPrefs: SharedPrefs,
    private val database: PracticePadRoomDatabase,
    private val entityMapper: EntityMapper
) {
    @OptIn(ExperimentalTime::class)
    suspend fun getActiveSet(): ExerciseSet {
        Log.d("XXX", "get active set")
        return entityMapper.map(database.exerciseSetDao().getSetFor(sharedPrefs.getActiveSetId()))
    }

    @OptIn(ExperimentalTime::class)
    suspend fun getAll(): List<ExerciseSet> =
        entityMapper.map(database.exerciseSetDao().getAll())

    @OptIn(ExperimentalTime::class)
    suspend fun getSetForId(id: Int): ExerciseSet =
        entityMapper.map(database.exerciseSetDao().getSetFor(id))
}
