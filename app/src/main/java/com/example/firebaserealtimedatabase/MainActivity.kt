package com.example.firebaserealtimedatabase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaserealtimedatabase.adapter.DataAdapter
import com.example.firebaserealtimedatabase.databinding.ActivityMainBinding
import com.example.firebaserealtimedatabase.model.Data
import com.example.firebaserealtimedatabase.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private val dataViewModel: DataViewModel by viewModels()
    private lateinit var dataAdapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dataAdapter = DataAdapter(listOf(), this)
        binding.recyclerViewList.adapter = dataAdapter
        binding.recyclerViewList.layoutManager = LinearLayoutManager(this)
        dataViewModel.dataList.observe(this) {
            if (it != null) {
                dataAdapter.updateData(it)
            } else {
                // Handle the case where the data is null
                // You can show a message or take appropriate action here
                Toast.makeText(this@MainActivity, "Data is null", Toast.LENGTH_SHORT).show()
            }
        }


    }


    fun onEditItemClick(item: Data){




    }

    fun onDeleteItemClick(item: Data){

    }
}


