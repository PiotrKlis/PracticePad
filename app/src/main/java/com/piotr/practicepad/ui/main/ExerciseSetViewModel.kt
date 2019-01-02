package com.piotr.practicepad.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSetEntity
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSetRepository

class ExerciseSetViewModel : ViewModel() {
    private var exerciseSetRepository: ExerciseSetRepository? = null

    init {
        exerciseSetRepository = ExerciseSetRepository()
    }

    fun getExerciseSets(): LiveData<List<ExerciseSetEntity>>? {
        return exerciseSetRepository?.getAllExerciseSets()
    }

    fun insertSet(exerciseSetEntity: ExerciseSetEntity) {
        exerciseSetRepository?.insertSet(exerciseSetEntity)
    }
}
