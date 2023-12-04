package com.aston.astonrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aston.astonrecyclerview.databinding.ActivityMainBinding
import com.aston.astonrecyclerview.model.RecyclerViewModel
import com.aston.astonrecyclerview.recycler.MyRecyclerViewAdapter
import com.aston.astonrecyclerview.repository.RecyclerViewRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.apache.tools.ant.taskdefs.Concat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewModel: RecyclerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        val viewModelFactory = RecyclerViewModelFactory(RecyclerViewRepository())
        recyclerViewModel = ViewModelProvider(this, viewModelFactory).get(RecyclerViewModel::class.java)

        val myRecyclerViewAdapter = MyRecyclerViewAdapter{ cotact ->
            if(recyclerViewModel.getTrashOnClickBoolean()){
                recyclerViewModel.addToDeleteList(cotact)
            } else {
                //create AlertDialog and Modification concat

            }
        }
        binding.mainActivityRecyclerView.adapter = myRecyclerViewAdapter

        val repComtacts = recyclerViewModel.getContacts()

        binding.mainActivityRecyclerView.layoutManager = LinearLayoutManager(this)

        myRecyclerViewAdapter.submitList(repComtacts)

        binding.appCompatImageButton.setOnClickListener {
            recyclerViewModel.trashOnClickTrue()
            binding.mainActiviytyButtonAddContact.visibility = View.INVISIBLE
            binding.mainActiviytyButtonDelete.visibility = View.VISIBLE
            binding.mainActiviytyButtonCancel.visibility = View.VISIBLE
        }

        binding.mainActiviytyButtonCancel.setOnClickListener {
            recyclerViewModel.trashOnClickFalse()
            binding.mainActiviytyButtonAddContact.visibility = View.VISIBLE
            binding.mainActiviytyButtonDelete.visibility = View.INVISIBLE
            binding.mainActiviytyButtonCancel.visibility = View.INVISIBLE
        }

        binding.mainActiviytyButtonDelete.setOnClickListener {

        }
    }

    fun createAlertDialog(concat: Concat){
        val dialog = MaterialAlertDialogBuilder(this)

    }

}