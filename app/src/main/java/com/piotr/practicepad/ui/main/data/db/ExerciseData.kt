package com.piotr.practicepad.ui.main.data.db

import com.piotr.practicepad.ui.main.utils.Helper

enum class ExerciseData(val time: Long, val title: String, val image: String) {
    SINGLE_STROKE_ROLL(Helper.secondsToMiliseconds(60), "single_stroke_roll", "single_stroke_roll"),
    SINGLE_STROKE_FOUR(Helper.secondsToMiliseconds(60), "single_stroke_four", "single_stroke_roll"),
    SINGLE_STROKE_SEVEN(Helper.secondsToMiliseconds(60), "single_stroke_seven", "single_stroke_seven"),
    MULTIPLE_BOUNCE_ROLL(Helper.secondsToMiliseconds(60), "multiple_bounce_roll", "multiple_bounce_roll"),
    TRIPLE_STROKE_ROLL(Helper.secondsToMiliseconds(60), "triple_stroke_roll", "triple_stroke_roll"),
    DOUBLE_STROKE_OPEN_ROLL(Helper.secondsToMiliseconds(60), "double_stroke_open_roll", "double_stroke_open_roll"),
    FIVE_STROKE_ROLL(Helper.secondsToMiliseconds(60), "five_stroke_roll", "six_stroke_roll"),
    SIX_STROKE_ROLL(Helper.secondsToMiliseconds(60), "six_stroke_roll", "six_stroke_roll"),
    SEVEN_STROKE_ROLL(Helper.secondsToMiliseconds(60), "seven_stroke_roll", "seven_stroke_roll"),
    DOUBLES(Helper.secondsToMiliseconds(60), "Doubles", "doubles"),
    TRIPLETS(Helper.secondsToMiliseconds(60), "Triplets", "triplets"),
    EMPTY(Helper.secondsToMiliseconds(60), "EMPTY", "EMPTY")
}
