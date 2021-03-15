package com.piotr.practicepad.views.exerciseSet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotr.practicepad.data.dao.UpdateExerciseSetTempoEntity
import com.piotr.practicepad.data.dao.UpdateExerciseSetTitleEntity
import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.entities.ExerciseSetEntityMapper
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExerciseSetViewModel @Inject constructor(
    private val exerciseSetRepository: ExerciseSetRepository,
    private val exerciseSetStateMapper: ExerciseSetStateMapper,
    private val exerciseSetEntityMapper: ExerciseSetEntityMapper,
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

    fun updateName(text: String) {
        viewModelScope.launch {
            state.value?.let {
                database
                    .exerciseSetDao()
                    .updateExerciseSetTitle(
                        UpdateExerciseSetTitleEntity(
                            id = it.id,
                            title = text
                        )
                    )
            }
        }
    }

    fun updateTempo(text: String) {
//        viewModelScope.launch {
//            state.value?.let {
//                database
//                    .exerciseSetDao()
//                    .updateExerciseSetTitle(
//                        UpdateExerciseSetTempoEntity(
//                            id = it.id,
//                            tempo = text
//                        )
//                    )
//            }
//        }
    }

    private fun updateDb(state: ExerciseSetState) {
        viewModelScope.launch {
            database.exerciseSetDao().updateExerciseList(
                exerciseSetEntityMapper.map(id = state.id, input = state.exerciseDetailsList)
            )
        }
    }
}
