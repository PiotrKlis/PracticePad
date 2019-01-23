package com.piotr.practicepad.ui.main.ExerciseList.external

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface ExerciseSetDao {
    @Query("SELECT * FROM exercisesetentity")
    fun getAll(): LiveData<List<ExerciseSetEntity>>

    @Insert(onConflict = REPLACE)
    fun insert(exerciseSetEntities: ArrayList<ExerciseSetEntity>?)

    @Query("DELETE from exercisesetentity")
    fun deleteAll()

    @Query("SELECT * FROM exercisesetentity WHERE id LIKE :id")
    fun getExerciseSetById(id: Int): LiveData<ExerciseSetEntity>?
}
