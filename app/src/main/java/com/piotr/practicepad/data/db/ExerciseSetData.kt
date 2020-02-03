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

private fun getShortWarmUp(): List<ExerciseData> = listOf(

)
private fun getMediumWarmUp(): List<ExerciseData> = listOf()
private fun getLongWarmUp(): List<ExerciseData> = listOf()
private fun getEverydayPractice(): List<ExerciseData> = listOf()
private fun getLeftHandPressure(): List<ExerciseData> = listOf()
private fun getRightHandPressure(): List<ExerciseData> = listOf()
private fun getSpeedBuilding(): List<ExerciseData> = listOf()
private fun getLongPractice(): List<ExerciseData> = listOf()
private fun getThousandSingles(): List<ExerciseData> = listOf()
private fun getThousandDoubles(): List<ExerciseData> = listOf()

/*
- Short warm-up, 3 min, tempo 110
- Warm-up, 5 min, tempo 100
- Long warm-up, 10 min, tempo 90
- Every day practice, 30 min, tempo 100
- Left hand pressure
- Right hand pressure
- Speed builduing
- Long practice - 45 min
- 1000 singles
- 2000 doubles
* */
