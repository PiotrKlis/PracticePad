package com.piotr.practicepad.exerciseList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.data.db.isSetActive
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExerciseListViewModel @Inject constructor(
    private val exerciseSetRepository: ExerciseSetRepository,
    private val sharedPrefs: SharedPrefs
) : ViewModel() {
    val state: LiveData<ExerciseListState> get() = mutableState
    private val mutableState = MutableLiveData(ExerciseListState())
    fun getExerciseSets() = viewModelScope.launch {
        mutableState.value = ExerciseListState(exerciseSetRepository.getAll())
    }
    fun onCheckboxClick(id: Int) = sharedPrefs.setActiveSetId(id)
    fun isSetActive(id: Int): Boolean = sharedPrefs.isSetActive(id)
}
