package com.piotr.practicepad.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.piotr.practicepad.exercise.Exercise
import com.piotr.practicepad.exercise.ExerciseEntity

class DataConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromExerciseList(value: List<ExerciseEntity>): String {
        val type = object : TypeToken<List<ExerciseEntity>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toExerciseList(value: String): List<ExerciseEntity> {
        val type = object : TypeToken<List<ExerciseEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}
