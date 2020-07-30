package com.example.learn.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.learn.Model.ProtoTransactionData;
import com.example.learn.Util.UtilVar;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OurDB extends SQLiteOpenHelper
{
   private Context our_context = null;

   public OurDB(Context context)
   {
      super(context, UtilVar.DATABASE_NAME, null, UtilVar.DATABASE_VERSION);
      our_context = context;
   }


   @Override
   public void onCreate(SQLiteDatabase db)
   {
      //create table _name
      String CREATE_ITEM_TABLE = "CREATE TABLE " + UtilVar.TABLE_NAME + "("
              + UtilVar.TRANSACTION_ID + " INTEGER PRIMARY KEY," + UtilVar.TRANS_TYPE + " TEXT,"
              + UtilVar.ACCT_TYPE + " TEXT," + UtilVar.OPEN_BALANCE + " DOUBLE," + UtilVar.CLOSE_BAANCE + " DOUBLE," + UtilVar.TRANS_AMOUNT + " DOUBLE" + ")";

      // creating our table
      db.execSQL(CREATE_ITEM_TABLE);

   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
   {
      String DROP_THE_TABLE = "DROP IF TABLE EXISTS";
      db.execSQL(DROP_THE_TABLE, new String[]{UtilVar.DATABASE_NAME});

      //Create a table again
      onCreate(db);

   }

   //Add Item to db
   public void addTransaction(ProtoTransactionData transaction)
   {
      SQLiteDatabase db = this.getWritableDatabase();

      ContentValues values = new ContentValues();

      values.put(UtilVar.TRANS_TYPE, transaction.getTransType());
      values.put(UtilVar.ACCT_TYPE, transaction.getAcctType());
      values.put(UtilVar.OPEN_BALANCE, transaction.getOpenBal());
      values.put(UtilVar.CLOSE_BAANCE, transaction.getClosingBal());
      values.put(UtilVar.TRANS_AMOUNT, transaction.getAmount());

      //date time stamp
   //   values.put(UtilVariables.KEY_DATE, java.lang.System.currentTimeMillis());

      //Insert the row
      db.insert(UtilVar.TABLE_NAME, null, values);

      Log.d("DBHandler", "addContact: " + "item added");

      //closing db connection!
      db.close();
   }



   //Get all Contacts
   public List<ProtoTransactionData> getAllTransactions()
   {
      List<ProtoTransactionData> transaction_list = new ArrayList<>();
     // String date_formatted = null;

      SQLiteDatabase db = this.getReadableDatabase();

      //Select all items in desc ord
      //     String selectAll = "SELECT * FROM " + UtilVariables.TABLE_NAME;

      Cursor cursor = db.query(UtilVar.TABLE_NAME,
              new String[]{ UtilVar.TRANSACTION_ID,
                      UtilVar.TRANS_TYPE,
                      UtilVar.ACCT_TYPE,
                      UtilVar.OPEN_BALANCE,
                      UtilVar.CLOSE_BAANCE,
                      UtilVar.TRANS_AMOUNT},
              null, null, null, null,
              UtilVar.TRANS_AMOUNT + " DESC");

      //Loop through our data
      if(cursor.moveToFirst())
      {
         do
         {
            ProtoTransactionData the_trans_obj = new ProtoTransactionData();

            the_trans_obj.setTransId(cursor.getInt(0));
            the_trans_obj.setTransType(cursor.getString(1));
            the_trans_obj.setAcctType(cursor.getString(2));
            the_trans_obj.setOpenBalance(cursor.getDouble(3));
            the_trans_obj.setClosingBalance(cursor.getDouble(4));
            the_trans_obj.setAmount(cursor.getDouble(5));

            //converting timestamp
        //    DateFormat myDateFormatter = DateFormat.getDateInstance();

        //    date_formatted = myDateFormatter.format(new Date(cursor.getLong(5)).getTime());

       //     the_item_obj.setDate(date_formatted);

            //add the_item_obj to our list
            transaction_list.add(the_trans_obj);

         }while(cursor.moveToNext());

      }

      return transaction_list;
   }



   public void clearDatabase()
   {
      String clearDBQuery = "DELETE FROM " + UtilVar.TABLE_NAME;
      SQLiteDatabase db = this.getReadableDatabase();
      db.execSQL(clearDBQuery);
   }



   //Get item count
   public int getCount()
   {
      String countQuery = "SELECT * FROM " + UtilVar.TABLE_NAME;
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.rawQuery(countQuery, null);

      return cursor.getCount();
   }
} // end OurDB
