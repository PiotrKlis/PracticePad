package com.piotr.practicepad.ui.main.ExerciseList

import com.piotr.practicepad.ui.main.Exercise.Exercise

data class ExerciseSet(val id: Int, val title: String, val exerciseList: ArrayList<Exercise>)

class DummyData {
    companion object {
        fun getEmptyExerciseSet(): ExerciseSet {
            return ExerciseSet(0, "Empty", arrayListOf())
        }

    }
}