package com.piotr.practicepad.data.repository

import com.piotr.practicepad.data.dao.ExerciseSetEntityUpdate
import com.piotr.practicepad.data.entities.ExerciseEntity
import com.piotr.practicepad.data.entities.ExerciseSetEntity
import com.piotr.practicepad.exercise.Exercise
import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.extensions.millisToSeconds
import com.piotr.practicepad.extensions.secondsToMilliseconds
import com.piotr.practicepad.utils.ResourceProvider
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class EntityMapper @Inject constructor(private val resourceProvider: ResourceProvider) {
    @ExperimentalTime
    fun map(input: List<ExerciseSetEntity>): List<ExerciseSet> = input.map { map(it) }

    @ExperimentalTime
    fun map(input: ExerciseSetEntity): ExerciseSet =
        ExerciseSet(
            id = input.id,
            title = input.title,
            tempo = input.tempo.toLong(),
            exercises = input.exercises.map { entity ->
                Exercise(
                    id = entity.id,
                    time = entity.time.toLong().secondsToMilliseconds(),
                    title = entity.title,
                    image = resourceProvider.getImageForString(entity.image)
                )
            }
        )

    fun mappo(id: Int, input: List<Exercise>): ExerciseSetEntityUpdate =
        ExerciseSetEntityUpdate(
            id = id,
            exercises = input.map { mapporus(it) })

    fun mapporus(exercise: Exercise): ExerciseEntity = ExerciseEntity(
        id = exercise.id,
        title = exercise.title,
        image = resourceProvider.getStringFromImage(exercise.image),
        time = exercise.time.millisToSeconds()
    )
}
