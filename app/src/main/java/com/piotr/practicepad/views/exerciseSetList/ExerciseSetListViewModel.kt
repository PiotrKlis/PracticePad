package com.piotr.practicepad.views.exerciseSetList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExerciseSetListViewModel @Inject constructor(
    private val exerciseSetRepository: ExerciseSetRepository,
    private val sharedPrefs: SharedPrefs
) : ViewModel() {
    val stateSet: LiveData<ExerciseSetListState> get() = mutableState
    private val mutableState = MutableLiveData(ExerciseSetListState())
    fun getExerciseSets() = viewModelScope.launch {
        mutableState.value = ExerciseSetListState(exerciseSetRepository.getAll())
    }
    fun onCheckboxClick(id: Int) = sharedPrefs.setActiveSetId(id)
    fun isSetActive(id: Int): Boolean = sharedPrefs.isSetActive(id)
}
