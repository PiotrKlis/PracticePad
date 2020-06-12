package com.piotr.practicepad.data.repository

import com.piotr.practicepad.data.db.ExerciseData
import com.piotr.practicepad.data.db.ExerciseSetData
import com.piotr.practicepad.di.utils.DataConverter
import com.piotr.practicepad.di.utils.Mapper
import com.piotr.practicepad.exercise.Exercise
import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.exerciseList.ExerciseSetEntity
import com.piotr.practicepad.extensions.secondsToMilliseconds
import javax.inject.Inject

class ExerciseSetEntityMapper @Inject constructor(private val dataConverter: DataConverter) :
    Mapper<Array<ExerciseSetData>, List<ExerciseSetEntity>> {
    fun map(input: List<ExerciseSetEntity>): List<ExerciseSet> =
        input.map {
            ExerciseSet(it.id, it.name, dataConverter.toExerciseList(it.exerciseList), it.tempo)
        }

    fun map(input: ExerciseSetEntity): ExerciseSet =
        ExerciseSet(
            input.id,
            input.name,
            dataConverter.toExerciseList(input.exerciseList),
            input.tempo
        )


    override fun map(input: Array<ExerciseSetData>): List<ExerciseSetEntity> =
        input.map {
            ExerciseSetEntity(
                it.id,
                it.name,
                dataConverter.fromExerciseList(mapToExerciseList(it.exerciseDataList)),
                it.tempo
            )
        }

    private fun mapToExerciseList(list: List<ExerciseData>): List<Exercise> =
        list.map { mapToExercise(it) }

    private fun mapToExercise(item: ExerciseData): Exercise =
        Exercise(
            item.time.secondsToMilliseconds(),
            item.exerciseData.title,
            item.exerciseData.image
        )
}
