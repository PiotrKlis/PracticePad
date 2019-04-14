package com.piotr.practicepad.ui.main.data.db

import com.piotr.practicepad.R
import com.piotr.practicepad.ui.main.utils.Helper

enum class ExerciseData(val time: Long, val title: String, val image: Int) {
    SINGLE_STROKE_ROLL(Helper.secondsToMiliseconds(6), "single_stroke_roll", R.drawable.ic_launcher_background),
    SINGLE_STROKE_FOUR(Helper.secondsToMiliseconds(6), "single_stroke_four", R.drawable.ic_launcher_foreground),
    SINGLE_STROKE_SEVEN(Helper.secondsToMiliseconds(6), "single_stroke_seven", R.drawable.ic_play_circle_filled_black_24dp),
    MULTIPLE_BOUNCE_ROLL(Helper.secondsToMiliseconds(6), "multiple_bounce_roll", R.drawable.ic_launcher_foreground),
    TRIPLE_STROKE_ROLL(Helper.secondsToMiliseconds(6), "triple_stroke_roll",R.drawable.ic_play_circle_filled_black_24dp) ,
    DOUBLE_STROKE_OPEN_ROLL(Helper.secondsToMiliseconds(6), "double_stroke_open_roll", R.drawable.ic_play_circle_filled_black_24dp),
    FIVE_STROKE_ROLL(Helper.secondsToMiliseconds(6), "five_stroke_roll", R.drawable.ic_home_black_24dp),
    SIX_STROKE_ROLL(Helper.secondsToMiliseconds(6), "six_stroke_roll", R.drawable.ic_notifications_black_24dp),
    SEVEN_STROKE_ROLL(Helper.secondsToMiliseconds(6), "seven_stroke_roll", R.drawable.ic_launcher_foreground),
    DOUBLES(Helper.secondsToMiliseconds(6), "Doubles", R.drawable.ic_play_circle_filled_black_24dp),
    TRIPLETS(Helper.secondsToMiliseconds(6), "Triplets", R.drawable.ic_notifications_black_24dp),
    EMPTY(Helper.secondsToMiliseconds(6), "EMPTY", R.drawable.ic_home_black_24dp)
}
