package com.piotr.practicepad.exerciseSetDetail

import com.piotr.practicepad.data.repository.Mapper
import com.piotr.practicepad.exerciseList.ExerciseSet

class ExerciseSetDetailsStateMapper :
    Mapper<ExerciseSet, ExerciseSetDetailsState> {
    override fun map(input: ExerciseSet): ExerciseSetDetailsState {
        return ExerciseSetDetailsState(
            input.name,
            input.tempo,
            input.exerciseList
        )
    }
}