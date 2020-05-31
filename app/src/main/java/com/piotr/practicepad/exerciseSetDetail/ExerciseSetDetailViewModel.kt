package com.piotr.practicepad.exerciseSetDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import javax.inject.Inject

class ExerciseSetDetailViewModel @Inject constructor(
    private val exerciseSetRepository: ExerciseSetRepository,
    private val exerciseSetDetailsStateMapper: ExerciseSetDetailsStateMapper
) : ViewModel() {
    val state: LiveData<ExerciseSetDetailsState> get() = mutableState
    private val mutableState = MutableLiveData(ExerciseSetDetailsState())

    fun renderData(id: Int) {
        mutableState.value =
            exerciseSetDetailsStateMapper.map(exerciseSetRepository.getSetForId(id))
    }
}
