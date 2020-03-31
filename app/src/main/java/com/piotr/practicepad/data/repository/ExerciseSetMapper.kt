package com.piotr.practicepad.data.repository

import com.piotr.practicepad.data.db.ExerciseData
import com.piotr.practicepad.data.db.ExerciseSetData
import com.piotr.practicepad.exercise.Exercise
import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.extensions.secondsToMiliseconds

class ExerciseSetMapper : Mapper<ExerciseSetData, ExerciseSet> {
    override fun map(input: ExerciseSetData): ExerciseSet {
        return ExerciseSet(
            input.id,
            input.title,
            mapToExerciseList(input.exerciseDataList),
            input.tempo
        )
    }

    private fun mapToExerciseList(list: List<ExerciseData>): ArrayList<Exercise> {
        val exerciseList = ArrayList<Exercise>()
        list.map { exerciseList.add(mapToExercise(it)) }
        return exerciseList
    }

    private fun mapToExercise(item: ExerciseData): Exercise {
        return Exercise(
            item.time.secondsToMiliseconds(),
            item.exerciseData.title,
            item.exerciseData.image
        )
    }
}
