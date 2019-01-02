package com.piotr.practicepad.ui.main.ExerciseList

import android.arch.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ExerciseSetRepository {
    var exerciseSetDao: ExerciseSetDao? = null

    init {
        val exerciseSetDatabase = ExercieSetDatabase.getInstance()
        exerciseSetDao = exerciseSetDatabase?.exerciseSetDao()
    }

    fun getAllExerciseSets(): LiveData<List<ExerciseSetEntity>>? {
        return exerciseSetDao?.getAll()
    }

    fun insertSet(exerciseSetEntity: ExerciseSetEntity) {
        GlobalScope.launch {
            exerciseSetDao?.insert(exerciseSetEntity)
        }
    }
}