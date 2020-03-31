package com.piotr.practicepad.di.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val viewModelsMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = viewModelsMap[modelClass]
            ?: viewModelsMap.asIterable().firstOrNull {
                modelClass.isAssignableFrom(it.key)
            }?.value
            ?: throw IllegalArgumentException("Could not find class $modelClass when instantiating a ViewModel")
        return try {
            @Suppress("UNCHECKED_CAST")
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    inline fun <reified TargetViewModel : ViewModel> provide(storeOwner: ViewModelStoreOwner): TargetViewModel {
        return ViewModelProvider(storeOwner, this).get(TargetViewModel::class.java)
    }
}