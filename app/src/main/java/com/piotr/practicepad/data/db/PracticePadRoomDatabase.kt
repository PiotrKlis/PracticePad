package com.piotr.practicepad.data.db

import android.content.Context
import androidx.annotation.StringRes
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.piotr.practicepad.R
import com.piotr.practicepad.data.dao.ExerciseDao
import com.piotr.practicepad.data.dao.ExerciseSetDao
import com.piotr.practicepad.data.repository.ExerciseSetEntityMapper
import com.piotr.practicepad.exercise.ExerciseEntity
import com.piotr.practicepad.exerciseList.ExerciseSetEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream

@Database(entities = [ExerciseSetEntity::class, ExerciseEntity::class], version = 1)
abstract class PracticePadRoomDatabase :
    RoomDatabase() {
    abstract fun exerciseSetDao(): ExerciseSetDao
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: PracticePadRoomDatabase? = null

        fun getInstance(
            context: Context,
            exerciseSetEntityMapper: ExerciseSetEntityMapper
        ): PracticePadRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context, exerciseSetEntityMapper).also { INSTANCE = it }
            }

        fun buildDatabase(context: Context, exerciseSetEntityMapper: ExerciseSetEntityMapper) =
            Room.databaseBuilder(
                context,
                PracticePadRoomDatabase::class.java,
                "practice_pad_database"
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    GlobalScope.launch(Dispatchers.IO) {
                        INSTANCE?.let { database -> saveJsonFiles(database) }
                    }
                }

                private suspend fun saveJsonFiles(database: PracticePadRoomDatabase) {
                    val exerciseSets = getExerciseSetsFromJson()
                    val exercises = getExercisesFromJson()
                    populateDatabase(database, exerciseSets, exercises)
                }

                private fun getExerciseSetsFromJson(): List<ExerciseSetEntity>? {
                    val jsonObj = JsonParser.parseString(
                        readJSONFromAsset(
                            context,
                            R.string.exercise_sets_json
                        )
                    ).asJsonArray
                    val type = object : TypeToken<List<ExerciseSetEntity>>() {}.type
                    return Gson().fromJson<List<ExerciseSetEntity>?>(jsonObj, type)
                }

                private fun getExercisesFromJson(): List<ExerciseEntity>? {
                    val jsonObj = JsonParser.parseString(
                        readJSONFromAsset(
                            context,
                            R.string.exercises_json
                        )
                    ).asJsonArray
                    val type = object : TypeToken<List<ExerciseEntity>>() {}.type
                    return Gson().fromJson<List<ExerciseEntity>?>(jsonObj, type)
                }
            }
            ).build()

        private fun readJSONFromAsset(context: Context, @StringRes jsonPath: Int): String {
            val json: String
            try {
                val inputStream: InputStream = context.assets.open(
                    context.getString(jsonPath)
                )
                json = inputStream.bufferedReader().use {
                    it.readText()
                }
            } catch (ex: Exception) {
                ex.localizedMessage
                return ""
            }
            return json
        }

        private suspend fun populateDatabase(
            database: PracticePadRoomDatabase,
            exerciseSets: List<ExerciseSetEntity>?,
            exercises: List<ExerciseEntity>?
        ) {
            //add safelet
            database.exerciseDao().insertAll(exercises)
            database.exerciseSetDao().insertAll(exerciseSets)
        }
    }
}
/*private class DeveloperDatabaseCallback(
    private val context: Context,
    private val scope: CoroutineScope) : RoomDatabase.Callback() {
    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        INSTANCE?.let { database ->
            scope.launch(Dispatchers.IO) {
                val jsonObj = JsonParser().parse(
                  readJSONFromAsset(context)).asJsonObject

        val companyType = object : TypeToken<Company>(){}.type
        val company: Company = Gson().fromJson(jsonObj, companyType)
        populateDatabase(database,company)*
}*/