package com.piotr.practicepad.ui.main.ExerciseList

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.piotr.practicepad.ui.main.Exercise.Exercise

@Entity(tableName = "ExerciseSet")
data class ExerciseSet(@PrimaryKey val id: Int, val exercises: List<Exercise>, val name: String)