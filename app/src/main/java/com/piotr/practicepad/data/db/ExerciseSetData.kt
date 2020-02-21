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
    ExerciseData(60, SINGLE_STROKE_ROLL),
    ExerciseData(120, DOUBLES),
    ExerciseData(90, TRIPLETS),
    ExerciseData(120, SINGLE_STROKE_ROLL),
    ExerciseData(180, DOUBLES),
    ExerciseData(90, SINGLE_STROKE_ROLL),
    ExerciseData(90, SINGLE_PARADIDDLE)
)

private val shortWarmUp = listOf(
    ExerciseData(30, SINGLE_STROKE_ROLL),
    ExerciseData(30, DOUBLES),
    ExerciseData(30, SINGLE_PARADIDDLE),
    ExerciseData(30, SINGLE_PARADIDDLE_DIDDLE),
    ExerciseData(30, TRIPLE_PARADIDDLE),
    ExerciseData(30, SINGLE_STROKE_ROLL),
    ExerciseData(30, DOUBLES),
    ExerciseData(30, SINGLE_PARADIDDLE),
    ExerciseData(30, SINGLE_PARADIDDLE_DIDDLE),
    ExerciseData(30, TRIPLE_PARADIDDLE)
)

private val flamExercises = listOf(
    ExerciseData(30, FLAM),
    ExerciseData(60, FLAM_ACCENT),
    ExerciseData(60, FLAM_TAP),
    ExerciseData(60, FLAMACUE),
    ExerciseData(60, FLAM_PARADIDDLE),
    ExerciseData(60, SINGLE_FLAMMED_MILL),
    ExerciseData(60, FLAM_PARADIDDLE_DIDDLE),
    ExerciseData(60, PATAFLAFLA),
    ExerciseData(30, SWISS_ARMY_TRIPLET),
    ExerciseData(60, INVERTED_FLAM_TAP),
    ExerciseData(60, FLAM_DRAG)
)

private val dragExercises = listOf(
    ExerciseData(30, DRAG),
    ExerciseData(45, SINGLE_DRAG_TAP),
    ExerciseData(45, DOUBLE_DRAG_TAP),
    ExerciseData(60, LESSON_25),
    ExerciseData(60, SINGLE_DRAGADIDDLE),
    ExerciseData(45, DRAG_PARADIDDLE_1),
    ExerciseData(45, DRAG_PARADIDDLE_2),
    ExerciseData(45, SINGLE_RATAMACUE),
    ExerciseData(45, DOUBLE_RATAMACUE),
    ExerciseData(45, TRIPLE_RATAMACUE)
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
