package com.piotr.practicepad.data.repository

import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.entities.ExerciseEntityMapper
import com.piotr.practicepad.views.exercise.Exercise
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val database: PracticePadRoomDatabase,
    private val exerciseEntityMapper: ExerciseEntityMapper
) {
    suspend fun getAll(): List<Exercise> = exerciseEntityMapper.mapExerciseEntities(database.exerciseDao().getAll())

    suspend fun getExerciseForText(query: String?): List<Exercise> =
        exerciseEntityMapper.mapExerciseEntities(database.exerciseDao().getExerciseForText("%$query%"))

    suspend fun getExerciseForId(exerciseId: Int): Exercise =
        exerciseEntityMapper.map(database.exerciseDao().getExerciseForId(exerciseId))
}
