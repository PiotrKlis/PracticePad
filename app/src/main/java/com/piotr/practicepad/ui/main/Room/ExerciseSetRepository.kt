package com.piotr.practicepad.ui.main.Room

import android.arch.lifecycle.LiveData
import com.piotr.practicepad.ui.main.Room.ExerciseSetDao
import com.piotr.practicepad.ui.main.Room.ExerciseSetEntity
import com.piotr.practicepad.ui.main.Room.PracticePadDatabase

class ExerciseSetRepository {
    var exerciseSetDao: ExerciseSetDao? = null

    init {
        val exerciseSetDatabase =
            PracticePadDatabase.getInstance()
        exerciseSetDao = exerciseSetDatabase?.exerciseSetDao()
    }

    fun getAllExerciseSets(): LiveData<List<ExerciseSetEntity>>? {
        return exerciseSetDao?.getAll()
    }
}