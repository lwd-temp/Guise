package com.houvven.guise.ui.mvi

import androidx.lifecycle.ViewModel


/**
 * ViewModel for MVI architecture, interacting with Reducer through Intent
 */
abstract class MviViewModel : ViewModel() {


    /**
     * Send an intent to the view model， which will be processed by the reducer
     *
     * @param intent The intent to be processed
     */
    fun sendIntent(intent: Intent) {
        reduce(intent)
    }

    /**
     * Distribute the intent to the corresponding processor through the intent distributor
     */
    protected abstract fun reduce(intent: Intent)
}

