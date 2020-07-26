package com.piotr.practicepad.exerciseSetDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExerciseSetDetailViewModel @Inject constructor(
    private val exerciseSetRepository: ExerciseSetRepository,
    private val exerciseSetDetailsStateMapper: ExerciseSetDetailsStateMapper
) : ViewModel() {
    val state: LiveData<ExerciseSetDetailsState> get() = mutableState
    private val mutableState = MutableLiveData(ExerciseSetDetailsState())

    fun renderData(id: Int) {
        viewModelScope.launch {
            mutableState.value =
                exerciseSetDetailsStateMapper.map(exerciseSetRepository.getSetForId(id))
        }
    }

    fun moveDown(position: Int) {
        mutableState.value?.let {
            val list = it.exerciseDetailsList.apply {
                val item = this[position]
                this.remove(item)
                this.add(position + 1, item)
            }
            mutableState.value = it.copy(exerciseDetailsList = list)
        }
    }

    fun moveUp(position: Int) {
        mutableState.value?.let {
            val list = it.exerciseDetailsList.apply {
                val item = this[position]
                this.remove(item)
                this.add(position - 1, item)
            }
            mutableState.value = it.copy(exerciseDetailsList = list)
        }
    }
}
