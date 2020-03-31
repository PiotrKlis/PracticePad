package com.piotr.practicepad.data.repository

import com.piotr.practicepad.data.db.ExerciseSetData
import com.piotr.practicepad.exerciseList.ExerciseSet

class ExerciseSetListMapper(private val exerciseSetMapper: Mapper<ExerciseSetData, ExerciseSet> = ExerciseSetMapper()) :
    Mapper<Array<ExerciseSetData>, List<ExerciseSet>> {

    override fun map(input: Array<ExerciseSetData>): List<ExerciseSet> {
        val mappedList: ArrayList<ExerciseSet> = arrayListOf()
        input.map { mappedList.add(exerciseSetMapper.map(it)) }
        return mappedList
    }
}
