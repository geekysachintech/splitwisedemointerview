package com.mrmobo.splitwisedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel(){

    val allExpenses: LiveData<List<Expense>> = repository.allExpenses

    fun insertExpense(expense: Expense) = viewModelScope.launch {
        repository.insertExpense(expense)

    }

    fun insertExpenseShare(expenseShare: ExpenseShare) = viewModelScope.launch {
        repository.insertExpenseShare(expenseShare)
    }

    fun getUserExpensesShares(userID: Int): LiveData<List<ExpenseShare>>{
        return repository.getUserExpensesShares(userID)
    }

    fun getTotalOwedByUser(userID: Int): LiveData<Double>{
        return repository.getTotalOwedByUser(userID)
    }

}

class ExpenseViewModelFactory(private val repository: ExpenseRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseViewModel::class.java)){
            return ExpenseViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown viewmodel class")
    }
}