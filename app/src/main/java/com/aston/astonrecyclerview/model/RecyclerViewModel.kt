package com.aston.astonrecyclerview.model

import androidx.lifecycle.ViewModel
import com.aston.astonrecyclerview.data.Contact
import com.aston.astonrecyclerview.repository.RecyclerViewRepository

class RecyclerViewModel(recyclerViewRepository: RecyclerViewRepository): ViewModel() {
    val repository = recyclerViewRepository

    fun getContacts():List<Contact>{
        return repository.contacts
    }
}