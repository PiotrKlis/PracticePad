package com.piotr.practicepad.ui.main.Exercise

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSetData
import com.piotr.practicepad.ui.main.Room.ExerciseRepository
import com.piotr.practicepad.ui.main.SharedPrefs.Companion.getActiveSet

class ExerciseViewModel: ViewModel() {
    private var exerciseRepository: ExerciseRepository? = null
    var list : MutableLiveData<ExerciseSetData>? = null

    init {
        exerciseRepository = ExerciseRepository()
        list = MutableLiveData()
    }

    fun getExercises(): LiveData<ExerciseSetData>? {
        list?.value = exerciseRepository?.getExercisesById(getActiveSet())
        return list
    }
}