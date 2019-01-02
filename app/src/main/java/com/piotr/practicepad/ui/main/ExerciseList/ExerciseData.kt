package com.piotr.practicepad.ui.main.ExerciseList

import com.piotr.practicepad.ui.main.utils.Helper

enum class ExerciseData(val time: Long, val title: String, val image: String, val id: Int) {
    SINGLE_STROKE_ROLL(Helper.secondsToMiliseconds(60), "single_stroke_roll", "single_stroke_roll", 1),
    SINGLE_STROKE_FOUR(Helper.secondsToMiliseconds(60), "single_stroke_four", "single_stroke_roll", 2),
    SINGLE_STROKE_SEVEN(Helper.secondsToMiliseconds(60), "single_stroke_seven", "single_stroke_seven", 3),
    MULTIPLE_BOUNCE_ROLL(Helper.secondsToMiliseconds(60), "multiple_bounce_roll", "multiple_bounce_roll", 4),
    TRIPLE_STROKE_ROLL(Helper.secondsToMiliseconds(60), "triple_stroke_roll", "triple_stroke_roll", 5),
    DOUBLE_STROKE_OPEN_ROLL(Helper.secondsToMiliseconds(60), "double_stroke_open_roll", "double_stroke_open_roll", 5),
    FIVE_STROKE_ROLL(Helper.secondsToMiliseconds(60), "five_stroke_roll", "six_stroke_roll", 6),
    SIX_STROKE_ROLL(Helper.secondsToMiliseconds(60), "six_stroke_roll", "six_stroke_roll", 7),
    SEVEN_STROKE_ROLL(Helper.secondsToMiliseconds(60), "seven_stroke_roll", "seven_stroke_roll", 8),
    DOUBLES(Helper.secondsToMiliseconds(60), "Doubles", "doubles", 10),
    TRIPLETS(Helper.secondsToMiliseconds(60), "Triplets", "triplets", 11);
}