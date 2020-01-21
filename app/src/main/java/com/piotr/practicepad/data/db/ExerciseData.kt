package com.piotr.practicepad.data.db

import com.piotr.practicepad.R

enum class ExerciseEnum(val time: Long, val title: String, val image: Int) {
    SINGLE_STROKE_ROLL(4000, "Single Stroke Roll", R.drawable.single_stroke_roll),
    SINGLE_STROKE_FOUR(6000, "Single Stroke Four", R.drawable.single_stroke_four),
    SINGLE_STROKE_SEVEN(7000, "Single Stroke Seven", R.drawable.single_stroke_seven),
    MULTIPLE_BOUNCE_ROLL(6000, "Multiple Bounce Roll", R.drawable.multiple_bounce_roll),
    TRIPLE_STROKE_ROLL(7000, "Triple Stroke Roll", R.drawable.triple_stroke_roll),
    DOUBLE_STROKE_OPEN_ROLL(5000, "Double Stroke Open Roll", R.drawable.single_stroke_roll),
    FIVE_STROKE_ROLL(5000, "Five Stroke Roll", R.drawable.five_stroke_roll),
    SIX_STROKE_ROLL(5000, "Six Stroke Roll", R.drawable.six_stroke_roll),
    SEVEN_STROKE_ROLL(5000, "Seven Stroke Roll", R.drawable.seven_stroke_roll),
    DOUBLES(5000, "Doubles", R.drawable.double_stroke_open_roll),
    TRIPLETS(4000, "Triplets", R.drawable.triple_stroke_roll),
    EMPTY(6000, "EMPTY", R.drawable.empty)
}

data class ExerciseData(val time: Long, val exerciseData: ExerciseEnum)