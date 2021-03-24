package com.piotr.practicepad.data.repository

import com.piotr.practicepad.data.dao.UpdateExerciseListEntity
import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.data.entities.ExerciseEntityMapper
import com.piotr.practicepad.data.entities.ExerciseSetEntityMapper
import com.piotr.practicepad.views.exerciseSetList.ExerciseSet
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class ExerciseSetRepository @Inject constructor(
    private val sharedPrefs: SharedPrefs,
    private val database: PracticePadRoomDatabase,
    private val exerciseSetEntityMapper: ExerciseSetEntityMapper,
    private val exerciseRepository: ExerciseRepository,
    private val exerciseEntityMapper: ExerciseEntityMapper
) {
    @OptIn(ExperimentalTime::class)
    suspend fun getActiveSet(): ExerciseSet =
        exerciseSetEntityMapper.map(
            database.exerciseSetDao().getSetFor(sharedPrefs.getActiveSetId())
        )

    @OptIn(ExperimentalTime::class)
    suspend fun getAll(): List<ExerciseSet> =
        exerciseSetEntityMapper.map(database.exerciseSetDao().getAll())

    @OptIn(ExperimentalTime::class)
    suspend fun getSetForId(id: Int): ExerciseSet =
        exerciseSetEntityMapper.map(database.exerciseSetDao().getSetFor(id))

    suspend fun updateExerciseSet(exerciseId: Int, exerciseSetId: Int) {
        val exerciseSet = getSetForId(exerciseSetId)
        val exercises = exerciseSet.exercises.toMutableList()
        exercises.add(exerciseRepository.getExerciseForId(exerciseId))
        database
            .exerciseSetDao()
            .updateExerciseList(UpdateExerciseListEntity(exerciseSetId, exerciseEntityMapper.mapExercises(exercises.toList())))
    }
}
