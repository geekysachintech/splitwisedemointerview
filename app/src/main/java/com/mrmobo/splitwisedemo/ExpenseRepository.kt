package com.mrmobo.splitwisedemo

import androidx.lifecycle.LiveData

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    val allExpenses: LiveData<List<Expense>> = expenseDao.getAllExpenses()

    suspend fun insertExpense(expense: Expense) {
        return expenseDao.insertExpense(expense)
    }

    suspend fun insertExpenseShare(expenseShare: ExpenseShare){
        expenseDao.insertExpenseShare(expenseShare)
    }

    fun getUserExpensesShares(userId: Int) : LiveData<List<ExpenseShare>>{
        return expenseDao.getUserExpensesShares(userId)
    }

    fun getTotalOwedByUser(userId: Int): LiveData<Double>{
        return expenseDao.getTotalOwedByUser(userId)
    }


}