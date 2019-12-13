package com.afterapps.hephaestus.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.*

// Base ViewModel to perform API calls and manage loading and error states
open class BaseViewModel : ViewModel() {

    // Job and Scope to launch/cancel coroutines
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // Error to be displayed by the view when the API sends and error and a private backing property to prevent modification
    private val _showConnectionError = LiveEvent<Boolean>()
    val showConnectionError: LiveData<Boolean>
        get() = _showConnectionError

    //Performs an API call and
    protected fun performApiCall(
        call: suspend () -> Unit,
        onSuccess: () -> Unit = {},
        onError: () -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                call.invoke()
                onSuccess()
            } catch (exception: Exception) {
                handleException(exception)
                onError()
                Log.e("BaseApiModel call: ", exception.toString())
            }
        }
    }

    @Throws(Exception::class)
    private fun handleException(exception: Exception) {
        exception.printStackTrace()
        _showConnectionError.value = true
    }

    // Cancel any pending coroutines
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}