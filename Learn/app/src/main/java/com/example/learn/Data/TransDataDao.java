package com.example.learn.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.learn.Model.TransactionData;
import java.util.List;

@Dao
public interface TransDataDao
{
   //CRUD
   @Insert
   void insert(TransactionData transData);

   @Query("DELETE FROM transaction_table")
   void deleteAll();

   @Query("DELETE FROM transaction_table WHERE trans_id = :id")
   int deleteTransaction(int id);

   //hoid on this one
 //  @Query("UPDATE transaction_table SET nodo_col = :nodoText WHERE id = :id")
   //int updateNoDoItem(int id, String nodoText);

   @Query("SELECT * FROM transaction_table ORDER BY trans_id DESC")
   LiveData<List<TransactionData>> getAllTransactions();




} // end TransDataDao interface
