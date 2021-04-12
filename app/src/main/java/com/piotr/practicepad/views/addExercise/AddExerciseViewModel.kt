package com.piotr.practicepad.views.addExercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotr.practicepad.data.repository.ExerciseRepository
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddExerciseViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val exerciseSetRepository: ExerciseSetRepository
) :
    ViewModel() {
    val state: LiveData<AddExerciseState> get() = mutableState
    private val mutableState = MutableLiveData(AddExerciseState())

    fun getExercises() {
        viewModelScope.launch {
            mutableState.value = AddExerciseState(exerciseRepository.getAll())
        }
    }

    fun getExercisesForText(query: String?) {
        viewModelScope.launch {
            mutableState.value = AddExerciseState(exerciseRepository.getExerciseForText(query))
        }
    }

    fun addExerciseToSet(exerciseId: Int, exerciseSetId: Int) {
        viewModelScope.launch {
            exerciseSetRepository.updateExerciseSet(exerciseId, exerciseSetId)
        }
    }

    fun addExerciseSet() {
        TODO("Not yet implemented")
    }
}
