package com.piotr.practicepad.exerciseSetDetail

import com.piotr.practicepad.utils.Mapper
import com.piotr.practicepad.exerciseList.ExerciseSet
import javax.inject.Inject

class ExerciseSetDetailsStateMapper @Inject constructor() :
    Mapper<ExerciseSet, ExerciseSetDetailsState> {
    override fun map(input: ExerciseSet): ExerciseSetDetailsState {
        return ExerciseSetDetailsState(
            id = input.id,
            name = input.title,
            tempo = input.tempo,
            exerciseDetailsList = input.exercises.toMutableList()
        )
    }
}