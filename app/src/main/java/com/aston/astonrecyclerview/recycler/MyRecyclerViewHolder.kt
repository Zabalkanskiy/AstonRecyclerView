package com.aston.astonrecyclerview.recycler

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aston.astonrecyclerview.R
import com.aston.astonrecyclerview.data.Contact
import com.aston.astonrecyclerview.databinding.MyRecyclerViewLayoutBinding
//I try use view binding
class MyRecyclerViewHolder(itemView: View) : ViewHolder(itemView) {

    val name: TextView = itemView.findViewById(R.id.my_recycler_text_view_name)
    val surname: TextView = itemView.findViewById(R.id.my_recycler_text_view_surname)
    val phoneNumber: TextView = itemView.findViewById(R.id.my_recycler_text_view_phone_number)
    val checkBox: CheckBox = itemView.findViewById(R.id.my_recycler_check_box)
    fun bind(model: Contact){
        name.text = model.name
        surname.text = model.surname
        phoneNumber.text = model.phoneNumber
    }
    fun onClick(){

    }
}