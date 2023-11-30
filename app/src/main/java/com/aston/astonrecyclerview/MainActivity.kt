package com.aston.astonrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.aston.astonrecyclerview.databinding.ActivityMainBinding
import com.aston.astonrecyclerview.model.RecyclerViewModel
import com.aston.astonrecyclerview.repository.RecyclerViewRepository

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
    }
}