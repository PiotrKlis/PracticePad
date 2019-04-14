package com.piotr.practicepad.ui.main.data.repository

import com.piotr.practicepad.ui.main.Exercise.Exercise
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet
import com.piotr.practicepad.ui.main.data.db.ExerciseData
import com.piotr.practicepad.ui.main.data.db.ExerciseSetData

class DataMapper {

    fun mapToExerciseSetList(list: Array<ExerciseSetData>): List<ExerciseSet> {
        val mappedList: ArrayList<ExerciseSet> = arrayListOf()
        for (item in list) {
            mappedList.add(mapToExerciseSet(item))
        }
        return mappedList
    }

    fun mapToExerciseSet(exerciseSetData: ExerciseSetData): ExerciseSet {
        return ExerciseSet(
            exerciseSetData.id,
            exerciseSetData.title,
            mapToExerciseList(exerciseSetData.exerciseDataList)
        )
    }

    private fun mapToExerciseList(list: List<ExerciseData>): ArrayList<Exercise> {
        val exerciseList = ArrayList<Exercise>()
        for (item in list) {
            exerciseList.add(mapToExercise(item))
        }
        return exerciseList
    }

    private fun mapToExercise(item: ExerciseData): Exercise {
        return Exercise(item.time, item.title, item.image)
    }
}