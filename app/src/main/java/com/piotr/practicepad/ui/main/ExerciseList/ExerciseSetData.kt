package com.piotr.practicepad.ui.main.ExerciseList

import com.piotr.practicepad.ui.main.Exercise.ExerciseData
import com.piotr.practicepad.ui.main.Exercise.ExerciseData.*

enum class ExerciseSetData(val id: Int, val title: String, val exerciseDataList: List<ExerciseData>) {
    BEGINNER(1, "Beginner", getBeginnerSet()),
    INTERMEDIATE(2, "Intermediate", getAdvancedSet()),
    MASTER(3, "Master", getMasterSet()),
}

private fun getBeginnerSet(): List<ExerciseData> {
    return listOf(SINGLE_STROKE_ROLL, SINGLE_STROKE_FOUR, SINGLE_STROKE_SEVEN)
}

private fun getAdvancedSet(): List<ExerciseData> {
    return listOf(MULTIPLE_BOUNCE_ROLL, TRIPLE_STROKE_ROLL, DOUBLE_STROKE_OPEN_ROLL, FIVE_STROKE_ROLL)
}

private fun getMasterSet(): List<ExerciseData> {
    return listOf(SIX_STROKE_ROLL, SEVEN_STROKE_ROLL, DOUBLES, TRIPLETS)
}
