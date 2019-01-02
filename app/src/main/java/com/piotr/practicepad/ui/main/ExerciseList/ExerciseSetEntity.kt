package com.piotr.practicepad.ui.main.ExerciseList

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "ExerciseSetEntity")
data class ExerciseSetEntity(
    @PrimaryKey val id: Int, val name: String
)