package com.aston.astonrecyclerview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aston.astonrecyclerview.model.RecyclerViewModel
import com.aston.astonrecyclerview.repository.RecyclerViewRepository

class RecyclerViewModelFactory(private val recyclerViewRepository: RecyclerViewRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecyclerViewModel::class.java)) {
            return RecyclerViewModel(recyclerViewRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
