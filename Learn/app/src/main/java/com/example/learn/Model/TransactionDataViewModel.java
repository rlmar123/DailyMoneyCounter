package com.example.learn.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.learn.Util.TransactionDataRepository;

import java.util.List;


//This class will be called in the pop up screens
public class TransactionDataViewModel extends AndroidViewModel
{
   private TransactionDataRepository transactionDataRepository; //connects to our repo
   private LiveData<List<TransactionData>> allNoDos;

   public TransactionDataViewModel(@NonNull Application application)
   {
      super(application);

      transactionDataRepository = new TransactionDataRepository(application);
      allNoDos = transactionDataRepository.getAllNoDos();
   }

   public LiveData<List<TransactionData>> getAllNoDos()
      {return allNoDos;}

   public void insert(TransactionData transactionData)
      {transactionDataRepository.insert(transactionData);}

   public int getCount()
   {
      return 1;
   }

} //end TransactionDataViewModel class
