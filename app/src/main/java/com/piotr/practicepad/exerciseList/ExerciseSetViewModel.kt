package com.piotr.practicepad.exerciseList

import androidx.lifecycle.ViewModel
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.data.db.isSetActive
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import javax.inject.Inject

class ExerciseSetViewModel @Inject constructor(
    private val exerciseSetRepository: ExerciseSetRepository,
    private val sharedPrefs: SharedPrefs
) : ViewModel() {
    fun getExerciseSets(): List<ExerciseSet> = exerciseSetRepository.getAll()
    fun onCheckboxClick(id: Int) = sharedPrefs.setActiveSetId(id)
    fun isSetActive(id: Int): Boolean = sharedPrefs.isSetActive(id)
}
