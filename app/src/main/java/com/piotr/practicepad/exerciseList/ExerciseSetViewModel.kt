package com.piotr.practicepad.exerciseList

import androidx.lifecycle.ViewModel
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import javax.inject.Inject

class ExerciseSetViewModel @Inject constructor(private val exerciseSetRepository: ExerciseSetRepository) :
    ViewModel() {
    fun getExerciseSets(): List<ExerciseSet> {
        return exerciseSetRepository.getAll()
    }
}
