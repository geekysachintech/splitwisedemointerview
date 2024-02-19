package com.mrmobo.splitwisedemo

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(tableName = "expense_shares", primaryKeys = ["expenseId", "userID"],
    foreignKeys = [ForeignKey(entity = Expense::class, parentColumns = ["id"], childColumns = ["expenseId"], onDelete = ForeignKey.CASCADE)]
    )
data class ExpenseShare(
    val expenseId: Int,
    val userId: Int,
    val shareAmount: Double
)
