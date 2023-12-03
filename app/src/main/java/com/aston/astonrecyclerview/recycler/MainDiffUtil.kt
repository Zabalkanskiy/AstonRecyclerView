package com.aston.astonrecyclerview.recycler

import androidx.recyclerview.widget.DiffUtil
import com.aston.astonrecyclerview.data.Contact

object MainDiffUtil : DiffUtil.ItemCallback<Contact>(){
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.equals(newItem)
    }
}