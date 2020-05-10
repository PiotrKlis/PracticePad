package com.piotr.practicepad.data.db

import com.piotr.practicepad.data.db.ExerciseEnum.*

enum class ExerciseSetData(
    val id: Int,
    val title: String,
    val exerciseDataList: List<ExerciseData>,
    val tempo: Int
) {
    EVERY_DAY(0, "", emptyList(), 0)
//    SINGLE_STROKE_PRESSURE(0, "Single Stroke Left & Right Hand Pressure", , 110),

}
/*- Quarter; 30s
- 8th left; 30s
- 8th right; 30s
- 16th; 30s
- Single stroke four; 30s
- Single stroke seven; 30s
- 16th; 30s
- 8th left; 30s
- 8th right; 30s
- 16th 30s
- Single stroke four; 30s
- Single stroke seven; 30s
- 16th; 30s
- Quarter
- 8th left; 30s
- 8th right; 30s
- 16th 30s
- Single stroke four; 30s
- Single stroke seven; 30s
- 16th; 30s
- 8th left; 30s
- 8th right; 30s
- 16th 30s
- Single stroke four; 30s
- Single stroke seven; 30s
- 16th; 30s
- 8th left; 30s
- 8th right; 30s
- Single stroke four; 30s
- Single stroke seven; 30s
- 16th*/

private val singleStrokePressure = ""

private fun getMediumWarmUp(): List<ExerciseData> = listOf()
private fun getLongWarmUp(): List<ExerciseData> = listOf()
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
