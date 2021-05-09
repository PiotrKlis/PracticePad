package com.piotr.practicepad.views.exerciseSetList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piotr.practicepad.data.db.PracticePadRoomDatabase
import com.piotr.practicepad.data.db.SharedPrefs
import com.piotr.practicepad.data.entities.ExerciseEntity
import com.piotr.practicepad.data.entities.ExerciseEntityMapper
import com.piotr.practicepad.data.entities.ExerciseSetEntity
import com.piotr.practicepad.data.repository.ExerciseSetRepository
import com.piotr.practicepad.views.exercise.Exercise
import com.piotr.practicepad.views.exerciseSet.ExerciseSetStateMapper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExerciseSetListViewModel @Inject constructor(
    private val exerciseSetRepository: ExerciseSetRepository,
    private val sharedPrefs: SharedPrefs,
    private val database: PracticePadRoomDatabase
) : ViewModel() {
    val state: LiveData<ExerciseSetListState> get() = mutableState
    private val mutableState = MutableLiveData(ExerciseSetListState())
    val newSetId: SharedFlow<Int> get() = mutableNewSetId
    private val mutableNewSetId: MutableSharedFlow<Int> = MutableSharedFlow()

    fun getExerciseSets() = viewModelScope.launch {
        mutableState.value = ExerciseSetListState(exerciseSetRepository.getAll())
    }

    fun onCheckboxClick(id: Int) = sharedPrefs.setActiveSetId(id)
    fun isSetActive(id: Int): Boolean = sharedPrefs.isSetActive(id)
    fun createNewExerciseSet() {
        viewModelScope.launch {
            val highestId: Int = database.exerciseSetDao().getAll().maxBy { it.id }?.id!!
            val newId = highestId + 1
            val newSet = ExerciseSetEntity(id = newId)
            database.exerciseSetDao().insert(newSet)
            mutableNewSetId.emit(newId)
        }
    }
}
