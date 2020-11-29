package com.piotr.practicepad.data.db

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.piotr.practicepad.R
import com.piotr.practicepad.data.dao.ExerciseDao
import com.piotr.practicepad.data.dao.ExerciseSetDao
import com.piotr.practicepad.data.dao.InitDao
import com.piotr.practicepad.data.dao.InitEntity
import com.piotr.practicepad.data.entities.ExerciseEntity
import com.piotr.practicepad.data.entities.ExerciseSetEntity
import com.piotr.practicepad.utils.Irrelevant
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.InputStream

@Database(entities = [ExerciseSetEntity::class, ExerciseEntity::class, InitEntity::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class PracticePadRoomDatabase :
    RoomDatabase() {
    abstract fun exerciseSetDao(): ExerciseSetDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun initDao(): InitDao

    companion object {
        val subject = PublishSubject.create<Irrelevant>()
        @Volatile
        var INSTANCE: PracticePadRoomDatabase? = null

        fun getInstance(context: Context): PracticePadRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                PracticePadRoomDatabase::class.java,
                "practice_pad_database"
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    GlobalScope.launch(Dispatchers.IO) {
                        INSTANCE?.let { database -> saveJsonFiles(database, context) }
                    }
                }
            }).build()

        private suspend fun saveJsonFiles(database: PracticePadRoomDatabase, context: Context) {
            val exerciseSets = getExerciseSetsFromJson(context)
            val exercises = getExercisesFromJson(context)
            populateDatabase(database, exerciseSets, exercises)
        }

        private fun getExerciseSetsFromJson(context: Context): List<ExerciseSetEntity> {
            val jsonObj = JsonParser.parseString(
                readJSONFromAsset(
                    context,
                    R.string.exercise_sets_json
                )
            ).asJsonArray
            val type = object : TypeToken<List<ExerciseSetEntity>>() {}.type
            return Gson().fromJson(jsonObj, type)
        }

        private fun getExercisesFromJson(context: Context): List<ExerciseEntity> {
            val jsonObj = JsonParser.parseString(
                readJSONFromAsset(
                    context,
                    R.string.exercises_json
                )
            ).asJsonArray
            val type = object : TypeToken<List<ExerciseEntity>>() {}.type
            return Gson().fromJson(jsonObj, type)
        }

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
            exerciseSets: List<ExerciseSetEntity>,
            exercises: List<ExerciseEntity>
        ) {
            Log.d("XXX", "populate db")
            database.exerciseDao().insertAll(exercises)
            database.exerciseSetDao().insertAll(exerciseSets)
            subject.onNext(Irrelevant.INSTANCE)
        }
    }
}
