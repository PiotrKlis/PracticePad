package com.piotr.practicepad.ui.main.ExerciseList

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.Exercise.ExerciseData
import com.piotr.practicepad.ui.main.ExerciseDataRepository
import com.piotr.practicepad.ui.main.ExerciseDataRepositoryImpl
import com.piotr.practicepad.ui.main.Room.ExerciseSetEntity
import com.piotr.practicepad.ui.main.Room.ExerciseSetRepository
import com.piotr.practicepad.ui.main.SharedPrefs

class ExerciseSetViewModel : ViewModel() {

    private var exerciseDataRepository: ExerciseDataRepository? = null

    init {
        exerciseDataRepository = ExerciseDataRepositoryImpl()
    }

    fun getExerciseSets(): Array<ExerciseSetData>? {
        return exerciseDataRepository?.getAllExerciseSets()
    }
}
