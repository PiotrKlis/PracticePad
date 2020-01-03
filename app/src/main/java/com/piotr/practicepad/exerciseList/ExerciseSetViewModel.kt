package com.piotr.practicepad.exerciseList

import androidx.lifecycle.ViewModel
import com.piotr.practicepad.data.repository.DataRepository
import com.piotr.practicepad.data.repository.ExerciseDataRepository

class ExerciseSetViewModel : ViewModel() {

    private var exerciseDataRepository: DataRepository =
        ExerciseDataRepository()

    fun getExerciseSets(): List<ExerciseSet> {
        return exerciseDataRepository.getAllExerciseSets()
    }
}
