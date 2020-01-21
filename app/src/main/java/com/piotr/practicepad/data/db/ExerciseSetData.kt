package com.piotr.practicepad.data.db

import com.piotr.practicepad.data.db.ExerciseEnum.*

enum class ExerciseSetData(
    val id: Int,
    val title: String,
    val exerciseDataList: List<ExerciseData>,
    val tempo: Int
) {
    BEGINNER(0, "Beginner", getBeginnerSet(), 100),
    INTERMEDIATE(1, "Intermediate", getAdvancedSet(), 80),
    MASTER(2, "Master", getMasterSet(), 120)
}

private fun getBeginnerSet(): List<ExerciseData> {
    return listOf(
        ExerciseData(8000, SINGLE_STROKE_ROLL),
        ExerciseData(7000, SINGLE_STROKE_FOUR),
        ExerciseData(4000, SINGLE_STROKE_SEVEN))
}

private fun getAdvancedSet(): List<ExerciseData> {
    return listOf(
        ExerciseData(6000, MULTIPLE_BOUNCE_ROLL),
        ExerciseData(3000, TRIPLETS),
        ExerciseData(4000, DOUBLES))
}

private fun getMasterSet(): List<ExerciseData> {
    return listOf(
        ExerciseData(5000, MULTIPLE_BOUNCE_ROLL),
        ExerciseData(4000, TRIPLE_STROKE_ROLL),
        ExerciseData(4000, DOUBLE_STROKE_OPEN_ROLL))
}
