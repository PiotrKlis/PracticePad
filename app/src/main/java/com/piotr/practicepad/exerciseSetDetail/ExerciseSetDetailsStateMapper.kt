package com.piotr.practicepad.exerciseSetDetail

import com.piotr.practicepad.data.db.ExerciseSetData
import com.piotr.practicepad.di.utils.Mapper
import com.piotr.practicepad.exerciseList.ExerciseSet
import com.piotr.practicepad.exerciseList.ExerciseSetEntity

class ExerciseSetDetailsStateMapper : Mapper<ExerciseSet, ExerciseSetDetailsState> {
    override fun map(input: ExerciseSet): ExerciseSetDetailsState {
        return ExerciseSetDetailsState(
            input.name,
            input.tempo,
            input.exerciseList.toMutableList()
        )
    }
}