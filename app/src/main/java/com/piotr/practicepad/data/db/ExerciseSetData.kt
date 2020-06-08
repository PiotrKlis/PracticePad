package com.piotr.practicepad.data.db

import com.piotr.practicepad.data.db.ExerciseEnum.*

enum class ExerciseSetData(
    val id: Int,
    val title: String,
    val exerciseDataList: List<ExerciseData>,
    val tempo: Int
) {
    SINGLE_STROKE_PRESSURE(0, "Single stroke pressure", singleStrokePressure, 110),
    EVERY_DAY_WORKOUT(1, "Everyday workout", everydayWorkout, 120),
    DOUBLES_4800(2, "4800 Doubles Challange", doubles4800, 120),
    DRAG_PRACTICE(3, "Drag Practice", dragPractice, 110),
    FLAM_PRACTICE(4, "Flam Practice", flamPractice, 110),
    SHORT_WARMUP(5, "Short Warmup", shortWarmup, 120),
}

private val shortWarmup = listOf(
    ExerciseData(30, DOUBLES_8TH),
    ExerciseData(30, DOUBLES_16TH),
    ExerciseData(30, LEFT_HAND_8TH),
    ExerciseData(30, RIGHT_HAND_8TH),
    ExerciseData(30, SINGLE_STROKE_16TH),
    ExerciseData(30, PARADIDDLE_16TH),
    ExerciseData(30, TRIPLE_STROKE_ROLL),
    ExerciseData(30, SEXTUPLETS_PARADIDDLE),
    ExerciseData(30, TRIPLETS_ONE_HAND_8TH),
    ExerciseData(30, SEXTUPLETS)
)

private val dragPractice = listOf(
    ExerciseData(45, DRAG),
    ExerciseData(45, DRAG_PARADIDDLE),
    ExerciseData(45, DRAG_PARADIDDLE_2),
    ExerciseData(45, SINGLE_DRAG_TAP),
    ExerciseData(45, SINGLE_DRAGADIDDLE),
    ExerciseData(120, DRAG_PARADIDDLE),
    ExerciseData(120, SINGLE_DRAG_TAP),
    ExerciseData(120, DRAG_PARADIDDLE_2),
    ExerciseData(120, SINGLE_DRAGADIDDLE)
)

private val flamPractice = listOf(
    ExerciseData(45, FLAM),
    ExerciseData(45, FLAM_ACCENT),
    ExerciseData(45, FLAM_DRAG),
    ExerciseData(45, FLAM_PARADIDDLE),
    ExerciseData(45, FLAM_TAP),
    ExerciseData(45, PATAFLAFLA),
    ExerciseData(45, SINGLE_FLAMMED_MILL),
    ExerciseData(120, FLAM_DRAG),
    ExerciseData(120, FLAM_TAP),
    ExerciseData(120, FLAM_ACCENT),
    ExerciseData(120, FLAM_PARADIDDLE),
    ExerciseData(120, SINGLE_FLAMMED_MILL),
    ExerciseData(120, PATAFLAFLA)
)

private val doubles4800 = listOf(ExerciseData(1200, DOUBLES_16TH))

private val everydayWorkout = listOf(
    ExerciseData(30, QUARTER),
    ExerciseData(60, DOUBLES_8TH),
    ExerciseData(60, LEFT_HAND_8TH),
    ExerciseData(60, RIGHT_HAND_8TH),
    ExerciseData(60, INVERTED_DOUBLES_8TH),
    ExerciseData(60, TRIPLETS),
    ExerciseData(60, FLAM_ACCENT),
    ExerciseData(60, SINGLE_STROKE_16TH),
    ExerciseData(60, DOUBLES_16TH),
    ExerciseData(60, PARADIDDLE_16TH),
    ExerciseData(60, FLAM_PARADIDDLE),
    ExerciseData(60, SEXTUPLETS),
    ExerciseData(60, SINGLE_STROKE_16TH),
    ExerciseData(60, SINGLE_DRAG_TAP),
    ExerciseData(60, DRAG_PARADIDDLE),
    ExerciseData(60, SINGLE_STROKE_16TH)
)

private val singleStrokePressure = listOf(
    ExerciseData(30, QUARTER),
    ExerciseData(30, LEFT_HAND_8TH),
    ExerciseData(30, RIGHT_HAND_8TH),
    ExerciseData(30, SINGLE_STROKE_16TH),
    ExerciseData(30, SINGLE_STROKE_FOUR),
    ExerciseData(30, SINGLE_STROKE_SEVEN),
    ExerciseData(30, SINGLE_STROKE_16TH),
    ExerciseData(30, LEFT_HAND_8TH),
    ExerciseData(30, RIGHT_HAND_8TH),
    ExerciseData(30, SINGLE_STROKE_16TH),
    ExerciseData(30, SINGLE_STROKE_FOUR),
    ExerciseData(30, SINGLE_STROKE_SEVEN),
    ExerciseData(30, SINGLE_STROKE_16TH),
    ExerciseData(30, QUARTER),
    ExerciseData(30, LEFT_HAND_8TH),
    ExerciseData(30, RIGHT_HAND_8TH),
    ExerciseData(30, SINGLE_STROKE_16TH),
    ExerciseData(30, SINGLE_STROKE_FOUR),
    ExerciseData(30, SINGLE_STROKE_SEVEN),
    ExerciseData(30, SINGLE_STROKE_16TH),
    ExerciseData(30, LEFT_HAND_8TH),
    ExerciseData(30, RIGHT_HAND_8TH),
    ExerciseData(30, SINGLE_STROKE_16TH),
    ExerciseData(30, SINGLE_STROKE_FOUR),
    ExerciseData(30, SINGLE_STROKE_SEVEN),
    ExerciseData(30, SINGLE_STROKE_16TH)
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
- 2000 doubles_8th
* */
