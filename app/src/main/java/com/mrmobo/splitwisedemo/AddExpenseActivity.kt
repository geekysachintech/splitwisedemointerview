package com.mrmobo.splitwisedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mrmobo.splitwisedemo.databinding.ActivityAddExpenseBinding

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExpenseBinding
    private lateinit var viewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val expenseDao = AppDatabase.getDatabase(this).expenseDao()
        viewModel = ViewModelProvider(this, ExpenseViewModelFactory(ExpenseRepository((expenseDao))))[ExpenseViewModel::class.java]
    }

    private fun setUpUI(){
        binding.btnSaveExpense.setOnClickListener {
            saveExpense()
        }
    }

    private fun saveExpense(){
        val amountText = binding.etAmount.text.toString()
        val description = binding.etDescription.text.toString()
        val amount = if (amountText.isNotEmpty()) amountText.toDoubleOrNull() ?: 0.0 else 0.0
        if (amount > 0 && description.isNotEmpty()){
            val expense = Expense(amount = amount, description = description, date = System.currentTimeMillis())
            viewModel.insertExpense(expense)
            setResult(RESULT_OK)
            finish()
        } else {
            Toast.makeText(this, "Please enter valid details", Toast.LENGTH_SHORT).show()
        }
    }
}