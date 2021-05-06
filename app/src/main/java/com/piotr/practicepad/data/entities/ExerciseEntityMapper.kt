package com.piotr.practicepad.data.entities

import com.piotr.practicepad.extensions.millisToSeconds
import com.piotr.practicepad.extensions.secondsToMillis
import com.piotr.practicepad.utils.ResourceProvider
import com.piotr.practicepad.views.exercise.Exercise
import javax.inject.Inject

class ExerciseEntityMapper @Inject constructor(private val resourceProvider: ResourceProvider) {
    fun mapExercises(exercises: List<Exercise>): List<ExerciseEntity> = exercises.map { map(it) }

    fun map(exercise: Exercise): ExerciseEntity = ExerciseEntity(
        id = exercise.id,
        title = exercise.title,
        image = resourceProvider.getStringFromImage(exercise.image),
        time = exercise.time.millisToSeconds()
    )

    fun mapExerciseEntities(input: List<ExerciseEntity>): List<Exercise> = input.map { map(it) }

    fun map(exerciseEntity: ExerciseEntity): Exercise = Exercise(
        id = exerciseEntity.id,
        title = exerciseEntity.title,
        time = exerciseEntity.time.toLong().secondsToMillis(),
        image = resourceProvider.getImageForString(exerciseEntity.image)
    )
}