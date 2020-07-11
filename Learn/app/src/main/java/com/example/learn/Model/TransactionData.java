package com.example.learn.Model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


//this will be used to store a row in the table
@Entity(tableName = "transaction_table")
public class TransactionData
{
   @PrimaryKey(autoGenerate = true)
   private int trans_id;

   @NonNull
   @ColumnInfo(name = "trans_type_col")
   private String trans_type;

   @NonNull
   @ColumnInfo(name = "acct_type_col")
   private String acct_type;

   @ColumnInfo(name = "open_bal_col")
   private double open_bal;

   @ColumnInfo(name = "closing_bal_col")
   private double closing_bal;

   @ColumnInfo(name = "amount_col")
   private double amount;

   // constructor
   public TransactionData(@NonNull String trans_type, @NonNull String acct_type, double open_bal, double closing_bal, double amount)
   {
      this.trans_type = trans_type;
      this.acct_type = acct_type;

      this.open_bal = open_bal;
      this.closing_bal = closing_bal;

      this.amount = amount;
   }

   public String getTrans_type()
      {return trans_type;}

    public String getAcct_type()
      {return acct_type;}

    public double getOpen_bal()
      {return open_bal;}

    public double getClosing_bal()
      {return closing_bal;}

    public int getTrans_id()
      {return trans_id;}

    public double getAmount(){
      return amount;
    }

    public void setTrans_id(int id)
      {trans_id = id;}



} // end of TransactionData class

