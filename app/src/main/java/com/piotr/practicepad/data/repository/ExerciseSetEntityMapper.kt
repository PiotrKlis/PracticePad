package com.piotr.practicepad.data.repository

import com.piotr.practicepad.di.utils.DataConverter
import com.piotr.practicepad.exercise.Exercise
import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.exerciseList.ExerciseSetEntity
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

class ExerciseSetEntityMapper @Inject constructor(private val dataConverter: DataConverter) {
    @ExperimentalTime
    fun map(input: List<ExerciseSetEntity>): List<ExerciseSet> =
        input.map { map(it) }

    @ExperimentalTime
    fun map(input: ExerciseSetEntity): ExerciseSet =
        ExerciseSet(
            id = input.id,
            title = input.title,
            tempo = input.tempo,
            exercises = input.exercises.map { entity ->
                Exercise(
                    entity.id,
                    entity.time.seconds.inMilliseconds.toLong(),
                    entity.title,
                    entity.image
                )
            }
        )
}
