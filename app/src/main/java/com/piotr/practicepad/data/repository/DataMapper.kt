package com.piotr.practicepad.data.repository

import com.piotr.practicepad.exercise.Exercise
import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.data.db.ExerciseData
import com.piotr.practicepad.data.db.ExerciseSetData

class DataMapper {

    fun mapToExerciseSetList(list: Array<ExerciseSetData>): List<ExerciseSet> {
        val mappedList: ArrayList<ExerciseSet> = arrayListOf()
        list.map { mappedList.add(mapToExerciseSet(it)) }
        return mappedList
    }

    fun mapToExerciseSet(exerciseSetData: ExerciseSetData): ExerciseSet {
        return ExerciseSet(
            exerciseSetData.id,
            exerciseSetData.title,
            mapToExerciseList(exerciseSetData.exerciseDataList),
            exerciseSetData.tempo
        )
    }

    private fun mapToExerciseList(list: List<ExerciseData>): ArrayList<Exercise> {
        val exerciseList = ArrayList<Exercise>()
        list.map { exerciseList.add(mapToExercise(it)) }
        return exerciseList
    }

    private fun mapToExercise(item: ExerciseData): Exercise {
        return Exercise(item.time, item.title, item.image)
    }
}