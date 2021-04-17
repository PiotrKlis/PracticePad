package com.piotr.practicepad.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_set_table")
class ExerciseSetEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "tempo") val tempo: Int = 90,
    @ColumnInfo(name = "exercises") val exercises: List<ExerciseEntity> = emptyList()
)