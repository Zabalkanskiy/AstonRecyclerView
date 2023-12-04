package com.aston.astonrecyclerview.repository

import com.aston.astonrecyclerview.data.Contact

class RecyclerViewRepository {
     var contacts :List<Contact>
    init {


         contacts = MutableList(100) {
            Contact(
                id = it,
                name = "Name $it",
                surname = "Surname $it",
                phoneNumber = "89300000000$it"
            )
        }
    }

    var trashOnclick: Boolean = false

    var listDeleteContact: MutableList<Contact> = mutableListOf()

    var positionList: MutableList<Int> = mutableListOf()
}