package com.piotr.practicepad.views.exerciseSet

import com.piotr.practicepad.views.exerciseSetList.ExerciseSet
import javax.inject.Inject

class ExerciseSetStateMapper @Inject constructor() {
     fun map(input: ExerciseSet): ExerciseSetState {
        return ExerciseSetState(
            id = input.id,
            name = input.title,
            tempo = input.tempo,
            exerciseDetailsList = input.exercises.toMutableList(),
            shouldHideDeleteButton = isCoreExercise(input.id)
        )
    }

    private fun isCoreExercise(id: Int): Boolean = coreExerciseIds.contains(id)
    private val coreExerciseIds = listOf(1000, 1001, 1002, 1003, 1004, 1005, 1006)
}
