package com.piotr.practicepad.ui.main.Exercise

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.ExerciseDataRepository
import com.piotr.practicepad.ui.main.ExerciseDataRepositoryImpl
import com.piotr.practicepad.ui.main.ExerciseList.ExerciseSetData
import com.piotr.practicepad.ui.main.Room.ExerciseRepository
import com.piotr.practicepad.ui.main.SharedPrefs.Companion.getActiveSet

class ExerciseViewModel: ViewModel() {
    private var exerciseRepository: ExerciseDataRepository = ExerciseDataRepositoryImpl()
    var list : MutableLiveData<ExerciseSetData>? = MutableLiveData()

    fun getActiveExerciseSet(): LiveData<ExerciseSetData>? {
        list?.value = exerciseRepository.getActiveExerciseSet(getActiveSet())
        return list
    }
}