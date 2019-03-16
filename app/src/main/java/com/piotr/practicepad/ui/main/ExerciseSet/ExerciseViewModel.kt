package com.piotr.practicepad.ui.main.ExerciseSet

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.piotr.practicepad.ui.main.ExerciseSetList.ExerciseSet
import com.piotr.practicepad.ui.main.data.SharedPrefs.Companion.getActiveSet
import com.piotr.practicepad.ui.main.data.DataRepository
import com.piotr.practicepad.ui.main.data.ExerciseDataRepositoryImpl

class ExerciseViewModel : ViewModel() {
    private var exerciseRepository: DataRepository = ExerciseDataRepositoryImpl()
    var list: MutableLiveData<ExerciseSet>? = MutableLiveData()

    fun getActiveExerciseSet(): MutableLiveData<ExerciseSet>? {
        list?.value = exerciseRepository.getActiveExerciseSet(getActiveSet())
        return list
    }
}