package com.piotr.practicepad.data.repository

import com.piotr.practicepad.data.entities.ExerciseSetEntity
import com.piotr.practicepad.exercise.Exercise
import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.utils.ResourceProvider
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

class EntityMapper @Inject constructor(private val resourceProvider: ResourceProvider) {
    @ExperimentalTime
    fun map(input: List<ExerciseSetEntity>): List<ExerciseSet> = input.map { map(it) }

    @ExperimentalTime
    fun map(input: ExerciseSetEntity): ExerciseSet =
        ExerciseSet(
            id = input.id,
            title = input.title,
            tempo = input.tempo,
            exercises = input.exercises.map { entity ->
                Exercise(
                    id = entity.id,
                    time = entity.time.seconds.inMilliseconds.toLong(),
                    title = entity.title,
                    image = resourceProvider.getImageForString(entity.image)
                )
            }
        )
}
