package com.piotr.practicepad.ui.main.ExerciseList

import android.arch.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.data.DataRepository
import com.piotr.practicepad.ui.main.data.ExerciseDataRepositoryImpl

class ExerciseSetViewModel : ViewModel() {

    private var exerciseDataRepository: DataRepository = ExerciseDataRepositoryImpl()

    fun getExerciseSets(): List<ExerciseSet> {
        return exerciseDataRepository.getAllExerciseSets()
    }
}
