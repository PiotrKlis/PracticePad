package com.piotr.practicepad.ui.main.ExerciseList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.ExerciseList.external.ExerciseSetEntity
import com.piotr.practicepad.ui.main.ExerciseList.external.ExerciseSetRepository

class ExerciseSetViewModel : ViewModel() {
    private var exerciseSetRepository: ExerciseSetRepository? = null

    init {
        exerciseSetRepository = ExerciseSetRepository()
    }

    fun getExerciseSets(): LiveData<List<ExerciseSetEntity>>? {
        return exerciseSetRepository?.getAllExerciseSets()
    }
}
