package com.piotr.practicepad.ui.main.ExerciseSetList

import android.arch.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.data.DataRepository
import com.piotr.practicepad.ui.main.data.ExerciseDataRepositoryImpl
import com.piotr.practicepad.ui.main.data.ExerciseSetData

class ExerciseSetViewModel : ViewModel() {

    private var exerciseDataRepository: DataRepository = ExerciseDataRepositoryImpl()

    fun getExerciseSets(): List<ExerciseSet> {
        return exerciseDataRepository.getAllExerciseSets()
    }
}
