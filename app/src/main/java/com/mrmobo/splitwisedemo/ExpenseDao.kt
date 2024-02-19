package com.mrmobo.splitwisedemo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenseShare(expenseShare: ExpenseShare)

    @Query("SELECT * FROM expenses")
    fun getAllExpenses(): LiveData<List<Expense>>

    @Query("SELECT * FROM expense_shares WHERE userId = :userId")
    fun getUserExpensesShares(userId: Int): LiveData<List<ExpenseShare>>

    @Query("SELECT SUM(shareAmount) FROM expense_shares WHERE userId = :userId")
    fun getTotalOwedByUser(userId: Int): LiveData<Double>

   /* @Query("SELECT SUM(amount) FROM expenses WHERE payerId =:userId")
    fun getTotalPaidByUser(userId: Int): LiveData<Double>*/
}