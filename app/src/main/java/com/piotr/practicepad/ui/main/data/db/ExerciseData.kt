package com.piotr.practicepad.ui.main.data.db

import com.piotr.practicepad.R

enum class ExerciseData(val time: Long, val title: String, val image: Int) {
    SINGLE_STROKE_ROLL(4000, "Single Stroke Roll", R.drawable.ic_launcher_background),
    SINGLE_STROKE_FOUR(6000, "Single Stroke Four", R.drawable.ic_launcher_foreground),
    SINGLE_STROKE_SEVEN(7000, "Single Stroke Seven", R.drawable.ic_play_circle_filled_black_24dp),
    MULTIPLE_BOUNCE_ROLL(6000, "Multiple Bounce Roll", R.drawable.ic_launcher_foreground),
    TRIPLE_STROKE_ROLL(7000, "Triple Stroke Roll", R.drawable.ic_play_circle_filled_black_24dp),
    DOUBLE_STROKE_OPEN_ROLL(5000, "Double Stroke Open Roll", R.drawable.ic_play_circle_filled_black_24dp),
    FIVE_STROKE_ROLL(5000, "Five Stroke Roll", R.drawable.ic_home_black_24dp),
    SIX_STROKE_ROLL(5000, "Siz Stroke Roll", R.drawable.ic_notifications_black_24dp),
    SEVEN_STROKE_ROLL(5000, "Seven Stroke Roll", R.drawable.ic_launcher_foreground),
    DOUBLES(5000, "Doubles", R.drawable.ic_play_circle_filled_black_24dp),
    TRIPLETS(4000, "Triplets", R.drawable.ic_notifications_black_24dp),
    EMPTY(6000, "EMPTY", R.drawable.ic_home_black_24dp)
}
