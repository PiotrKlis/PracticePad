package com.piotr.practicepad.ui.main.data.db

import com.piotr.practicepad.R

enum class ExerciseData(val time: Long, val title: String, val image: Int) {
    SINGLE_STROKE_ROLL(600, "single_stroke_roll", R.drawable.ic_launcher_background),
    SINGLE_STROKE_FOUR(600, "single_stroke_four", R.drawable.ic_launcher_foreground),
    SINGLE_STROKE_SEVEN(600, "single_stroke_seven", R.drawable.ic_play_circle_filled_black_24dp),
    MULTIPLE_BOUNCE_ROLL(600, "multiple_bounce_roll", R.drawable.ic_launcher_foreground),
    TRIPLE_STROKE_ROLL(600, "triple_stroke_roll", R.drawable.ic_play_circle_filled_black_24dp),
    DOUBLE_STROKE_OPEN_ROLL(600, "double_stroke_open_roll", R.drawable.ic_play_circle_filled_black_24dp),
    FIVE_STROKE_ROLL(600, "five_stroke_roll", R.drawable.ic_home_black_24dp),
    SIX_STROKE_ROLL(6000, "six_stroke_roll", R.drawable.ic_notifications_black_24dp),
    SEVEN_STROKE_ROLL(600, "seven_stroke_roll", R.drawable.ic_launcher_foreground),
    DOUBLES(600, "Doubles", R.drawable.ic_play_circle_filled_black_24dp),
    TRIPLETS(600, "Triplets", R.drawable.ic_notifications_black_24dp),
    EMPTY(600, "EMPTY", R.drawable.ic_home_black_24dp)
}
