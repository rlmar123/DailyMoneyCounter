package com.example.learn.Util;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.example.learn.Data.TransDataDao;
import com.example.learn.Data.TransactionDataRoomDatabase;
import com.example.learn.Model.TransactionData;


public class TransactionDataRepository
{
   private TransDataDao transDataDao;
   private LiveData<List<TransactionData>> allNoDos;

   public TransactionDataRepository(Application application) {
      //Get data from a remote API and then put it on a diff. list
      TransactionDataRoomDatabase db = TransactionDataRoomDatabase.getDatabase(application);
      transDataDao = db.transDataDao();
      allNoDos = transDataDao.getAllTransactions();
   }

   public LiveData<List<TransactionData>> getAllNoDos() {
      return allNoDos;
   }

   public void insert(TransactionData transactionData){
      new insertAsyncTask(transDataDao).execute(transactionData);
   }


   private class insertAsyncTask extends AsyncTask<TransactionData, Void, Void> {
      private TransDataDao asyncTaskDao;
      public insertAsyncTask(TransDataDao dao) {
         asyncTaskDao = dao;

      }


      //..insertion?
      @Override
      protected Void doInBackground(TransactionData... params) {
         //[obj1, obj2....]
         asyncTaskDao.insert(params[0]);
         return null;
      }
   }



} // end TransactionDataRepository class
