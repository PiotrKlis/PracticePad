package com.piotr.practicepad.data.db

import com.piotr.practicepad.data.db.ExerciseEnum.*

enum class ExerciseSetData(
    val id: Int,
    val title: String,
    val exerciseDataList: List<ExerciseData>,
    val tempo: Int
) {
    EVERY_DAY(0, "Every Day Practice", everyDayPractice, 110),
    SHORT_WARMUP(1, "Short Warmup", shortWarmUp, 120),
    FLAM_EXERCISE(2, "Flam Exercises", flamExercises, 90),
    DRAG_EXERCISE(3, "Drag Exercises", dragExercises, 110)
}

private val everyDayPractice = listOf(
    ExerciseData(6, SINGLE_STROKE_ROLL),
    ExerciseData(12, DOUBLES),
    ExerciseData(9, TRIPLETS),
    ExerciseData(12, SINGLE_STROKE_ROLL),
    ExerciseData(18, DOUBLES),
    ExerciseData(9, SINGLE_STROKE_ROLL),
    ExerciseData(9, SINGLE_PARADIDDLE)
)

private val shortWarmUp = listOf(
    ExerciseData(3, SINGLE_STROKE_ROLL),
    ExerciseData(3, DOUBLES),
    ExerciseData(3, SINGLE_PARADIDDLE),
    ExerciseData(3, SINGLE_PARADIDDLE_DIDDLE),
    ExerciseData(3, TRIPLE_PARADIDDLE),
    ExerciseData(3, SINGLE_STROKE_ROLL),
    ExerciseData(3, DOUBLES),
    ExerciseData(3, SINGLE_PARADIDDLE),
    ExerciseData(3, SINGLE_PARADIDDLE_DIDDLE),
    ExerciseData(3, TRIPLE_PARADIDDLE)
)

private val flamExercises = listOf(
    ExerciseData(3, FLAM),
    ExerciseData(6, FLAM_ACCENT),
    ExerciseData(6, FLAM_TAP),
    ExerciseData(6, FLAMACUE),
    ExerciseData(6, FLAM_PARADIDDLE),
    ExerciseData(6, SINGLE_FLAMMED_MILL),
    ExerciseData(6, FLAM_PARADIDDLE_DIDDLE),
    ExerciseData(6, PATAFLAFLA),
    ExerciseData(3, SWISS_ARMY_TRIPLET),
    ExerciseData(6, INVERTED_FLAM_TAP),
    ExerciseData(6, FLAM_DRAG)
)

private val dragExercises = listOf(
    ExerciseData(3, DRAG),
    ExerciseData(4, SINGLE_DRAG_TAP),
    ExerciseData(4, DOUBLE_DRAG_TAP),
    ExerciseData(6, LESSON_25),
    ExerciseData(6, SINGLE_DRAGADIDDLE),
    ExerciseData(4, DRAG_PARADIDDLE_1),
    ExerciseData(4, DRAG_PARADIDDLE_2),
    ExerciseData(4, SINGLE_RATAMACUE),
    ExerciseData(4, DOUBLE_RATAMACUE),
    ExerciseData(4, TRIPLE_RATAMACUE)
)

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
