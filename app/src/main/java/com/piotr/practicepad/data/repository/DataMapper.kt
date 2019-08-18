package com.piotr.practicepad.ui.main.data.repository

import com.piotr.practicepad.ui.main.Exercise.ExerciseState
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSet
import com.piotr.practicepad.ui.main.data.db.ExerciseData
import com.piotr.practicepad.ui.main.data.db.ExerciseSetData

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
            mapToExerciseList(exerciseSetData.exerciseDataList)
        )
    }

    private fun mapToExerciseList(list: List<ExerciseData>): ArrayList<ExerciseState> {
        val exerciseList = ArrayList<ExerciseState>()
        list.map { exerciseList.add(mapToExercise(it)) }
        return exerciseList
    }

    private fun mapToExercise(item: ExerciseData): ExerciseState {
        return ExerciseState(item.time, item.title, item.image)
    }
}