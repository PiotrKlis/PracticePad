package com.piotr.practicepad.views.exerciseSet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.entities.EntityMapper
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExerciseSetViewModel @Inject constructor(
    private val exerciseSetRepository: ExerciseSetRepository,
    private val exerciseSetStateMapper: ExerciseSetStateMapper,
    private val entityMapper: EntityMapper,
    private val database: PracticePadRoomDatabase
) : ViewModel() {
    val state: LiveData<ExerciseSetState> get() = mutableState
    private val mutableState = MutableLiveData(ExerciseSetState())

    fun renderData(id: Int) {
        viewModelScope.launch {
            mutableState.value =
                exerciseSetStateMapper.map(exerciseSetRepository.getSetForId(id))
        }
    }

    fun moveDown(position: Int) {
        mutableState.value?.let { state ->
            val list = state.exerciseDetailsList.apply {
                val item = this[position]
                this.remove(item)
                this.add(position + 1, item)
            }
            mutableState.value = state.copy(exerciseDetailsList = list)
            updateDb(state)
        }
    }

    fun moveUp(position: Int) {
        mutableState.value?.let { state ->
            val list = state.exerciseDetailsList.apply {
                val item = this[position]
                this.remove(item)
                this.add(position - 1, item)
            }
            mutableState.value = state.copy(exerciseDetailsList = list)
            updateDb(state)
        }
    }


    fun delete(position: Int) {
        mutableState.value?.let { state ->
            val list = state.exerciseDetailsList.apply {
                val item = this[position]
                this.remove(item)
            }
            mutableState.value = state.copy(exerciseDetailsList = list)
            updateDb(state)
        }
    }

    private fun updateDb(state: ExerciseSetState) {
        viewModelScope.launch {
            database.exerciseSetDao().update(
                entityMapper.mappo(id = state.id, input = state.exerciseDetailsList)
            )
        }
    }
}
