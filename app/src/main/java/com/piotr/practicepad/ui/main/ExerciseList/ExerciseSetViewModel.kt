package com.piotr.practicepad.ui.main.ExerciseList

import android.arch.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.data.DataRepository
import com.piotr.practicepad.ui.main.data.ExerciseDataRepository

class ExerciseSetViewModel : ViewModel() {

    private var exerciseDataRepository: DataRepository = ExerciseDataRepository()

    fun getExerciseSets(): List<ExerciseSet> {
        return exerciseDataRepository.getAllExerciseSets()
    }
}
