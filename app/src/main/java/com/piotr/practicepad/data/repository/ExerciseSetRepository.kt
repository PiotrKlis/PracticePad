package com.piotr.practicepad.data.repository

import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.data.entities.EntityMapper
import com.piotr.practicepad.views.exerciseSetList.ExerciseSet
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class ExerciseSetRepository @Inject constructor(
    private val sharedPrefs: SharedPrefs,
    private val database: PracticePadRoomDatabase,
    private val entityMapper: EntityMapper
) {
    @OptIn(ExperimentalTime::class)
    suspend fun getActiveSet(): ExerciseSet =
        entityMapper.map(database.exerciseSetDao().getSetFor(sharedPrefs.getActiveSetId()))

    @OptIn(ExperimentalTime::class)
    suspend fun getAll(): List<ExerciseSet> =
        entityMapper.map(database.exerciseSetDao().getAll())

    @OptIn(ExperimentalTime::class)
    suspend fun getSetForId(id: Int): ExerciseSet =
        entityMapper.map(database.exerciseSetDao().getSetFor(id))
}
