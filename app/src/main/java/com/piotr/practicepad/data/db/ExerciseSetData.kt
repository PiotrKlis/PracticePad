package com.piotr.practicepad.data.db

import com.piotr.practicepad.data.db.ExerciseEnum.*

enum class ExerciseSetData(
    val id: Int,
    val title: String,
    val exerciseDataList: List<ExerciseData>,
    val tempo: Int
) {
    EVERY_DAY(0, "Every Day Practice", everyDayPractice, 100)
}

private val everyDayPractice = listOf(
    ExerciseData(60, SINGLE_STROKE_ROLL),
    ExerciseData(120, DOUBLES),
    ExerciseData(90, TRIPLETS),
    ExerciseData(120, SINGLE_STROKE_ROLL),
    ExerciseData(180, DOUBLES),
    ExerciseData(90, SINGLE_STROKE_ROLL),
    ExerciseData(90, SINGLE_PARADIDDLE)
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
