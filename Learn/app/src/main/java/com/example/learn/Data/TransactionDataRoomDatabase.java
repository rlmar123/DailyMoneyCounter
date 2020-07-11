package com.example.learn.Data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.learn.Model.TransactionData;

// I added exportSchema = false to bypass warning
@Database(entities = {TransactionData.class}, version = 1, exportSchema = false)
public abstract class TransactionDataRoomDatabase extends RoomDatabase
{
   public static volatile TransactionDataRoomDatabase INSTANCE;
   public abstract TransDataDao transDataDao(); // this is implemented in another SDK file, hit the green arrow

   public static TransactionDataRoomDatabase getDatabase(final Context context) {
      if (INSTANCE == null) {
         synchronized (TransactionDataRoomDatabase.class) {
            if (INSTANCE == null) {
               //create our db
               INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                       TransactionDataRoomDatabase.class, "transaction_data_database")
                       .addCallback(roomDatabaseCallBack)
                       .build();
            }
         }
      }

      return INSTANCE;
   }

   private static RoomDatabase.Callback roomDatabaseCallBack =
           new RoomDatabase.Callback() {
              @Override
              public void onOpen(@NonNull SupportSQLiteDatabase db) {
                 super.onOpen(db);
                 new PopulateDbAsync(INSTANCE).execute();
              }
           };
   private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
      public final TransDataDao transDataDao;

      public PopulateDbAsync(TransactionDataRoomDatabase db) {
         transDataDao = db.transDataDao();
      }

      @Override
      protected Void doInBackground(Void... voids) {
         //noDoDao.deleteAll(); //removes all items from our table
         //for testing
//            NoDo noDo = new NoDo("Buy a new Ferrari");
//            noDoDao.insert(noDo);
//
//            noDo = new NoDo("Buy a Big house");
//            noDoDao.insert(noDo);

         return null;
      }
   }





} //end abstract class TransactionDataRoomDatabase
