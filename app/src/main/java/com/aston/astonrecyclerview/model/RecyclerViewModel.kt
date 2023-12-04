package com.aston.astonrecyclerview.model

import androidx.lifecycle.ViewModel
import com.aston.astonrecyclerview.data.Contact
import com.aston.astonrecyclerview.repository.RecyclerViewRepository

class RecyclerViewModel(recyclerViewRepository: RecyclerViewRepository) : ViewModel() {
    private  val repository = recyclerViewRepository

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

    fun removeToDeleteList(contact: Contact){
        repository.listDeleteContact.removeAll{it.equals(contact)}

    }

    fun removeContactFromDelete(): List<Contact>{
        val listDeleted = repository.listDeleteContact
        val newList = repository.contacts.toMutableList()
        listDeleted.forEach { contact -> newList.remove(contact) }
        return newList
    }
    fun addToDeletePositionList(position: Int){
        repository.positionList.add(position)
    }

    fun removeToDeletePositionList(position: Int){
        repository.positionList.removeAll { it == position }
    }


    fun changeContact(newContact: Contact, position: Int): List<Contact>{
     // val newContacts =  repository.contacts.find { concat -> concat.id == newContact.id }?.copy(id = newContact.id, name = newContact.name, surname = newContact.surname, phoneNumber = newContact.phoneNumber)
        val newList = repository.contacts.toMutableList()
        newList.set(position, newContact)
       return newList.toList()
    }

    fun saveNewList(newListContact: List<Contact>){

        repository.contacts = newListContact
    }

    fun addContact(newContact: Contact):List<Contact>{
        val newList = repository.contacts.toMutableList()
        newList.add(newContact)
        return newList
    }
}