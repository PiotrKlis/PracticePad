package com.piotr.practicepad.ui.main.Exercise

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.piotr.practicepad.ui.main.ExerciseList.ExercieSetDatabase

class ExerciseRepository {
    var exerciseDao: ExerciseDao? = null

    init {
        val exerciseSetDatabase = ExercieSetDatabase.getInstance()
        exerciseDao = exerciseSetDatabase?.exerciseDao()
    }

    fun getExercisesById(exerciseSetId: Int): LiveData<List<Exercise>>? {
        val exerciseEntities = exerciseDao?.getExercisesById(exerciseSetId)
        return mapToDomain(exerciseEntities)
    }

    private fun mapToDomain(exerciseEntities: LiveData<List<ExerciseEntity>>?): LiveData<List<Exercise>>? {
        if (exerciseEntities != null) {
            return Transformations.map(exerciseEntities) { exerciseEntity -> mapToExercise(exerciseEntity) }
        } else {
            return null
        }
    }

    fun mapToExercise(listOfExerciseEntities: List<ExerciseEntity>): List<Exercise>? {
        val exercises: ArrayList<Exercise> = arrayListOf()
        for (exerciseEntity in listOfExerciseEntities) {
            val exercise = Exercise(exerciseEntity.id, exerciseEntity.time, exerciseEntity.title, exerciseEntity.image)
            exercises.add(exercise)
        }
        return exercises
    }
}
