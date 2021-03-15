package com.piotr.practicepad.data.entities

import com.piotr.practicepad.data.dao.UpdateExerciseListEntity
import com.piotr.practicepad.extensions.secondsToMilliseconds
import com.piotr.practicepad.utils.ResourceProvider
import com.piotr.practicepad.views.exercise.Exercise
import com.piotr.practicepad.views.exerciseSetList.ExerciseSet
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class ExerciseSetEntityMapper @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val exerciseEntityMapper: ExerciseEntityMapper
) {
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

    fun map(id: Int, input: List<Exercise>): UpdateExerciseListEntity =
        UpdateExerciseListEntity(
            id = id,
            exercises = input.map { exerciseEntityMapper.map(it) })


}
