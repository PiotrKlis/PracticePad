package com.piotr.practicepad.exercise

import com.piotr.practicepad.R
import java.util.ArrayList

data class Exercise(val time: Long = 0L, val name: String = "", val image: Int = R.drawable.empty) {
    fun ArrayList<Exercise>.getNextExerciseName(index: Int): String {
        var result = "Last one"
        if (index + 1 < this.size) {
            result = this[index + 1].name
        }
        return result
    }

    fun ArrayList<Exercise>.getOverallTime(): Long =
        this.map { it.time }
            .sum()
}

