package com.piotr.practicepad.ui.main.Exercise

import android.arch.lifecycle.LiveData

class ExerciseViewModel {
    private var exerciseRepository: ExerciseRepository? = null

    init {
        exerciseRepository = ExerciseRepository()
    }

    fun getExercises(): LiveData<List<Exercise>>? {
        return exerciseRepository?.getExercisesById(getActiveSet())
    }
}