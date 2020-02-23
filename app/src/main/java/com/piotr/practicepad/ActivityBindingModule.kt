package com.piotr.practicepad

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.piotr.practicepad.exercise.ExerciseFragment
import com.piotr.practicepad.exercise.ExerciseViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector(
        modules = [
            ExerciseModule::class,
            MediaPlayerModule::class
        ]
    )
    abstract fun mainActivity(): MainActivity
}

@Module
abstract class ExerciseModule {
    @ContributesAndroidInjector
    abstract fun contributesExerciseFragment(): ExerciseFragment

    @Binds
    @IntoMap
    @ViewModelKey(ExerciseViewModel::class)
    abstract fun bindsExerciseViewModel(viewModel: ExerciseViewModel): ViewModel
}

@Module
class MediaPlayerModule {
    @Provides
    fun provideMediaPlayer(context: Context): MediaPlayer =
        MediaPlayer.create(context, R.raw.low_seiko_sq50)
}
