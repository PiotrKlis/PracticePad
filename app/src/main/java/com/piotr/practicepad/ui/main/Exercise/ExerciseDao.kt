package com.piotr.practicepad.ui.main.Exercise

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exerciseentity")
    fun getAll(): LiveData<List<ExerciseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(exerciseEntity: ExerciseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(exerciseEntity: MutableList<Exercise>)

    @Query("DELETE from exerciseentity")
    fun deleteAll()
}