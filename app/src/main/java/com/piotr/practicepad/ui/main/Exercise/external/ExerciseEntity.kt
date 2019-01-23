package com.piotr.practicepad.ui.main.Exercise.external

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "ExerciseEntity")
data class ExerciseEntity(val id: List<Int>, val time: Long, @PrimaryKey val title: String, val image: String)
