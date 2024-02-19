package com.mrmobo.splitwisedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mrmobo.splitwisedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val expenseDao = AppDatabase.getDatabase(this).expenseDao()
        viewModel = ViewModelProvider(this, ExpenseViewModelFactory(ExpenseRepository((expenseDao))))[ExpenseViewModel::class.java]
        setupUI()
    }

    private fun setupUI(){
        binding.btnAddExpense.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * 1. Users Table, how many users are available
     * 2. to create group
     * 3. in group feature to add expense
     */
}