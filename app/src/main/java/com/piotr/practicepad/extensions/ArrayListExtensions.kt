package com.piotr.practicepad.extensions

import com.piotr.practicepad.exercise.Exercise
import java.util.ArrayList

fun List<Exercise>.getNextExerciseName(index: Int): String {
    var result = "Last one"
    if (index + 1 < this.size) {
        result = this[index + 1].name
    }
    return result
}

fun List<Exercise>.getOverallTime(): Long =
    this.map { it.time }
        .sum()
