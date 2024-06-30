package com.rbackent.app.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rbackent.app.ui.network.Repository

class ViewModelFactory(private val application: Application = Application(), private val repository: Repository =Repository()) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(com.rbackent.app.ui.viewmodel.ViewModel::class.java) -> {
                return com.rbackent.app.ui.viewmodel.ViewModel(application, repository) as T
            }
            else ->throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}