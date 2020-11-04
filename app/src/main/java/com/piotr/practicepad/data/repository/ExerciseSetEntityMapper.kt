package com.piotr.practicepad.data.repository

import com.piotr.practicepad.di.utils.DataConverter
import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.exerciseList.ExerciseSetEntity
import javax.inject.Inject

class ExerciseSetEntityMapper @Inject constructor(private val dataConverter: DataConverter) {
    fun map(input: List<ExerciseSetEntity>): List<ExerciseSet> =
        input.map {
            ExerciseSet(
                id = it.id,
                title = it.title,
                tempo = it.tempo,
                exercises = dataConverter.toExerciseList(it.exercises)
            )
        }

    fun map(input: ExerciseSetEntity): ExerciseSet =
        ExerciseSet(
            id = input.id,
            title = input.title,
            exercises = dataConverter.toExerciseList(input.exercises),
            tempo = input.tempo
        )
}
