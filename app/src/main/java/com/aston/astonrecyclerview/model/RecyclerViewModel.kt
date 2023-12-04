package com.aston.astonrecyclerview.model

import androidx.lifecycle.ViewModel
import com.aston.astonrecyclerview.data.Contact
import com.aston.astonrecyclerview.repository.RecyclerViewRepository

class RecyclerViewModel(recyclerViewRepository: RecyclerViewRepository) : ViewModel() {
    val repository = recyclerViewRepository

    fun getContacts(): List<Contact> {
        return repository.contacts
    }

    fun trashOnClickTrue() {
        repository.trashOnclick = true
    }

    fun trashOnClickFalse() {
        repository.trashOnclick = false
    }

    fun getTrashOnClickBoolean(): Boolean {
        return repository.trashOnclick
    }

    fun addToDeleteList(contact: Contact) {
        repository.listDeleteContact.add(
            contact
        )

    }
}