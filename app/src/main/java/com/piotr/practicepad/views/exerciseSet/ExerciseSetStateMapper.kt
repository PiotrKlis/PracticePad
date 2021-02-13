package com.piotr.practicepad.views.exerciseSet

import com.piotr.practicepad.utils.Mapper
import com.piotr.practicepad.views.exerciseSetList.ExerciseSet
import javax.inject.Inject

class ExerciseSetStateMapper @Inject constructor() :
    Mapper<ExerciseSet, ExerciseSetState> {
    override fun map(input: ExerciseSet): ExerciseSetState {
        return ExerciseSetState(
            id = input.id,
            name = input.title,
            tempo = input.tempo,
            exerciseDetailsList = input.exercises.toMutableList()
        )
    }
}