package com.piotr.practicepad.views.addExercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotr.practicepad.data.repository.ExerciseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddExerciseSetViewModel @Inject constructor(private val exerciseRepository: ExerciseRepository) :
    ViewModel() {
    val state: LiveData<AddExerciseState> get() = mutableState
    private val mutableState = MutableLiveData(AddExerciseState())

    fun getExercises() {
        viewModelScope.launch {
            mutableState.value = AddExerciseState(exerciseRepository.getAll())
        }
    }
}
