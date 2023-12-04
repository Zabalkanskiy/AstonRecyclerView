package com.aston.astonrecyclerview.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aston.astonrecyclerview.R
import com.aston.astonrecyclerview.data.Contact
import com.aston.astonrecyclerview.databinding.MyRecyclerViewLayoutBinding

class MyRecyclerViewAdapter(private val onClickAction: (Contact) -> Unit) : ListAdapter<Contact, MyRecyclerViewHolder>(MainDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_recycler_view_layout, parent, false)
        // val viewHolder = MyRecyclerViewLayoutBinding.inflate(inflater)
        // may be setOnclickListener add to viewHolder
        val viewHolder =  MyRecyclerViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val item = getItem(viewHolder.adapterPosition)
            onClickAction(item)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

       // holder.itemView.setOnClickListener {
      //      if()
      //  }
    }
}