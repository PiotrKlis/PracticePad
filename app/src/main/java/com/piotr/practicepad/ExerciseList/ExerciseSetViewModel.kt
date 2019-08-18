package com.piotr.practicepad.ui.main.ExerciseList

import androidx.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.data.repository.DataRepository
import com.piotr.practicepad.ui.main.data.repository.ExerciseDataRepository

class ExerciseSetViewModel : ViewModel() {

    private var exerciseDataRepository: DataRepository =
        ExerciseDataRepository()

    fun getExerciseSets(): List<ExerciseSet> {
        return exerciseDataRepository.getAllExerciseSets()
    }
}
