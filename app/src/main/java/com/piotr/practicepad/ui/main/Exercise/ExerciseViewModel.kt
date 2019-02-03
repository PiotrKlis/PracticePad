package com.piotr.practicepad.ui.main.Exercise

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.Room.ExerciseRepository
import com.piotr.practicepad.ui.main.SharedPrefs.Companion.getActiveSet

class ExerciseViewModel: ViewModel() {
    private var exerciseRepository: ExerciseRepository? = null

    init {
        exerciseRepository = ExerciseRepository()
    }

    fun getExercises(): LiveData<List<Exercise>>? {
        return exerciseRepository?.getExercisesById(getActiveSet())
    }
}