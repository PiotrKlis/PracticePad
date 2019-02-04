package com.piotr.practicepad.ui.main.ExerciseList

import android.arch.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.ExerciseDataRepository
import com.piotr.practicepad.ui.main.ExerciseDataRepositoryImpl

class ExerciseSetViewModel : ViewModel() {

    private var exerciseDataRepository: ExerciseDataRepository? = null

    init {
        exerciseDataRepository = ExerciseDataRepositoryImpl()
    }

    fun getExerciseSets(): Array<ExerciseSetData>? {
        return exerciseDataRepository?.getAllExerciseSets()
    }
}
