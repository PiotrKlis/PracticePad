package com.piotr.practicepad.views.exerciseSet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotr.practicepad.data.dao.UpdateExerciseListEntity
import com.piotr.practicepad.data.dao.UpdateExerciseSetTempoEntity
import com.piotr.practicepad.data.dao.UpdateExerciseSetTitleEntity
import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.entities.ExerciseEntityMapper
import com.piotr.practicepad.data.entities.ExerciseSetEntityMapper
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
class ExerciseSetViewModel @Inject constructor(
    private val exerciseSetRepository: ExerciseSetRepository,
    private val exerciseSetStateMapper: ExerciseSetStateMapper,
    private val exerciseSetEntityMapper: ExerciseSetEntityMapper,
    private val exerciseEntityMapper: ExerciseEntityMapper,
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
        state.value?.let { state ->
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
        state.value?.let { state ->
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

    fun updateTempo(tempo: Int) {
        viewModelScope.launch {
            state.value?.let {
                database
                    .exerciseSetDao()
                    .updateExerciseSetTempo(
                        UpdateExerciseSetTempoEntity(
                            id = it.id,
                            tempo = tempo
                        )
                    )
            }
        }
    }

    private fun updateDb(state: ExerciseSetState) {
        viewModelScope.launch {
            database.exerciseSetDao().updateExerciseList(
                exerciseSetEntityMapper.map(id = state.id, input = state.exerciseDetailsList)
            )
        }
    }

    fun updateTime(time: Long, exerciseId: Int) {
        viewModelScope.launch {
            state.value?.let { state ->
                val exerciseSet =
                    exerciseSetEntityMapper.map(database.exerciseSetDao().getSetFor(state.id))
                exerciseSet.exercises
                    .find { it.id == exerciseId }
                    ?.let { exercise ->
                        val updatedExercise = exercise.copy(time = time)
                        val mutableList = exerciseSet.exercises.toMutableList()
                        val exerciseIndex = mutableList.indexOf(exercise)
                        mutableList.remove(exercise)
                        mutableList.add(exerciseIndex, updatedExercise)
                        database.exerciseSetDao().updateExerciseList(
                            UpdateExerciseListEntity(
                                state.id,
                                exerciseEntityMapper.mapExercises(mutableList.toList())
                            )
                        )
                    }
            }
        }
    }

    fun createNewSetId(): Int {
        TODO("Not yet implemented")
    }
}
