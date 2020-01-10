package com.piotr.practicepad.data.db

import com.piotr.practicepad.data.db.ExerciseData.*

enum class ExerciseSetData(val id: Int, val title: String, val exerciseDataList: List<ExerciseData>, val tempo: Int) {
    BEGINNER(0, "Beginner", getBeginnerSet(), 100),
    INTERMEDIATE(1, "Intermediate", getAdvancedSet(), 80),
    MASTER(2, "Master", getMasterSet(), 120)
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
